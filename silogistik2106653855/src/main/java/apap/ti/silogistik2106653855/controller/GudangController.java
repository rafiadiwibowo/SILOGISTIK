package apap.ti.silogistik2106653855.controller;

import apap.ti.silogistik2106653855.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653855.dto.response.GudangMapper;
import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;
import apap.ti.silogistik2106653855.repository.BarangDb;
import apap.ti.silogistik2106653855.repository.GudangDb;
import apap.ti.silogistik2106653855.repository.KaryawanDb;
import apap.ti.silogistik2106653855.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106653855.service.BarangService;
import apap.ti.silogistik2106653855.service.GudangBarangService;
import apap.ti.silogistik2106653855.service.GudangService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GudangController {

    @Autowired
    private GudangBarangService gudangBarangService;
    @Autowired
    private BarangService barangService;
    @Autowired
    private GudangService gudangService;
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;
    @Autowired
    GudangDb gudangDb;
    @Autowired
    BarangDb barangDb;
    @Autowired
    KaryawanDb karyawanDb;


    @GetMapping("/")
    public String home(Model model) {
        long jumlahGudang = gudangDb.count();
        long jumlahBarang = barangDb.count();
        long jumlahPermintaanPengiriman = permintaanPengirimanDb.count();
        long jumlahKaryawan = karyawanDb.count();


        model.addAttribute("jumlahGudang", jumlahGudang);
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("jumlahPermintaanPengiriman", jumlahPermintaanPengiriman);
        model.addAttribute("jumlahKaryawan", jumlahKaryawan);


        return "home";
    }

    @GetMapping("gudang")
    public String listGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        return "viewall-daftar-gudang";
    }

    @GetMapping("gudang/{id}")
    public String detailGudang(@PathVariable("id") Long id, Model model) {
        var gudang = gudangService.getGudangById(id);
        var gudangBarang = gudangBarangService.findBarangByGudang(gudang);

        model.addAttribute("gudang", gudang);
        model.addAttribute("gudangBarang", gudangBarang);

        return "view-detail-gudang";
    }

    @GetMapping("gudang/cari-barang")
    public String cariBarang(
            @RequestParam(name = "sku_barang", required = false) String sku_barang,
            Model model) {

        List<Barang> listBarang = barangService.getAllBarang();

        if (sku_barang != null && !sku_barang.isEmpty()) {
            List<GudangBarang> listGudangBarang = barangService.findBarangBySku(sku_barang).getListGudangBarang();
            model.addAttribute("sku", sku_barang);
            model.addAttribute("listGudangBarang", listGudangBarang);
        }

        model.addAttribute("listBarang", listBarang);
        return "view-cari-barang";
    }

    @GetMapping("gudang/{id}/restock-barang")
    public String restockBarang(@PathVariable("id") Long id, Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        Gudang gudang = gudangService.getGudangById(id);

        var restockBarangRequestDTO = new RestockBarangRequestDTO();
        model.addAttribute("barangRestockRequestDTO", restockBarangRequestDTO);
        model.addAttribute("gudang", gudang);
        model.addAttribute("idGudang", id);
        model.addAttribute("listBarang", listBarang);


        return "form-restock-gudang";
    }

    @PostMapping("/gudang/{id}/restock-barang")
    public String restockBarang(
            @PathVariable Long id,
            @ModelAttribute RestockBarangRequestDTO restockBarangRequestDTO,
            Model model) {
        Gudang gudang = gudangService.getGudangById(id);

        for (RestockBarangRequestDTO.RestockBarangItem item : restockBarangRequestDTO.getListRestockBarang()) {
            String sku = item.getSku();
            int stok = item.getJumlahStok();
            Barang barang = barangService.getBarangById(sku);
            GudangBarang gudangBarang = gudangService.getGudangBarangByGudangAndBarang(gudang, barang);

            gudangService.updateStockGudangBarang(gudangBarang, gudang, barang, stok);
        }
        model.addAttribute("gudang", gudang);
        return "success-restok";
    }

    @PostMapping(value = "/gudang/{id}/restock-barang", params = {"addRow"})
    public String addRowBarang(
            @PathVariable Long id,
            @ModelAttribute RestockBarangRequestDTO restockBarangRequestDTO,
            Model model
    ) {
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);

        Gudang gudang = gudangService.getGudangById(id);

        model.addAttribute("gudang", gudang);
        model.addAttribute("idGudang", id);

        if (restockBarangRequestDTO.getListRestockBarang() == null || restockBarangRequestDTO.getListRestockBarang().size() == 0) {
            restockBarangRequestDTO.setListRestockBarang(new ArrayList<>());
        }

        restockBarangRequestDTO.getListRestockBarang().add(new RestockBarangRequestDTO.RestockBarangItem());

        model.addAttribute("barangRestockRequestDTO", restockBarangRequestDTO);
        return "form-restock-gudang";
    }

}
