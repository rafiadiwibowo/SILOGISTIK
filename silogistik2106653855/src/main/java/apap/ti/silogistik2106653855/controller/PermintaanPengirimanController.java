package apap.ti.silogistik2106653855.controller;

import apap.ti.silogistik2106653855.dto.response.PermintaanPengirimanMapper;
import apap.ti.silogistik2106653855.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653855.model.Karyawan;
import apap.ti.silogistik2106653855.model.PermintaanPengiriman;
import apap.ti.silogistik2106653855.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106653855.service.BarangService;
import apap.ti.silogistik2106653855.service.KaryawanService;
import apap.ti.silogistik2106653855.service.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106653855.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    private PermintaanPengirimanDb permintaanPengirimanDb;

    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{id}")
    public String detailBarang(@PathVariable("id") Long id, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.findPermintaanPengirimanById(id);
        var permintaanPengorimanBarang = permintaanPengirimanBarangService.findBarangByPermintaan(permintaanPengiriman);

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        model.addAttribute("permintaanPengirimanBarang", permintaanPengorimanBarang);

        return "view-detail-permintaan-barang";
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String permintaanPengirimanForm(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        var barangDTO = new Barang();

        model.addAttribute("barangDTO", barangDTO);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);



        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowPermintaanPengirimanBarang(
            @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,
            Model model
    ) {
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);

        List<Barang> listBarang = barangService.getAllBarang();

        if (permintaanPengirimanDTO.getListPermintaanPengirimanBarang() == null || permintaanPengirimanDTO.getListPermintaanPengirimanBarang().size() == 0) {
            permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        PermintaanPengirimanBarang permintaanPengirimanBarangBaru = new PermintaanPengirimanBarang();

        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().add(permintaanPengirimanBarangBaru);

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, BindingResult bindingResult, Model model) {

        Karyawan karyawan = karyawanService.getKaryawanById(permintaanPengirimanDTO.getKaryawanId());
        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
        permintaanPengirimanService.tambahPermintaanPengiriman(permintaanPengiriman, karyawan);


        return "success-restok";
    }

    @GetMapping("permintaan-pengiriman/{id}/cancel")
    public String cancelPermintaanPengiriman(@PathVariable("id") Long id) {
        PermintaanPengiriman getPermintaanPengiriman = permintaanPengirimanService.findPermintaanPengirimanById(id);
        if (permintaanPengirimanService.isCancelable(getPermintaanPengiriman)) {
            getPermintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanDb.save(getPermintaanPengiriman);
            return "success-cancel-permintaan-pengiriman";
        } else {
            return "error-cancel";
        }
    }
}
