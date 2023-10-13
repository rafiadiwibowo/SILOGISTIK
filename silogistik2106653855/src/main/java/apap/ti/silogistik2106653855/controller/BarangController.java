package apap.ti.silogistik2106653855.controller;

import apap.ti.silogistik2106653855.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.response.BarangMapper;
import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.GudangBarang;
import apap.ti.silogistik2106653855.service.BarangService;
import apap.ti.silogistik2106653855.service.GudangBarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BarangController {

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @Autowired
    private BarangMapper barangMapper;

    @GetMapping("barang")
    public String listBarang(Model model) {

        List<Barang> listBarang = barangService.getAllBarang();
        List<Integer> listStok = new ArrayList<>();

        for (Barang barang : listBarang) {
            int stock = barangService.findStok(barang);
            listStok.add(stock);
        }

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listStok", listStok);


        return "viewall-daftar-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBuku(Model model) {
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);

        return "form-tambah-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO createBarangRequestDTO, Model model) {
        Barang barang = new Barang();
        barang.setTipeBarang(createBarangRequestDTO.getTipeBarang());

        String generatedSKU = barangService.generateSKU(barang);
        barang.setSku(generatedSKU);
        barang.setMerk(createBarangRequestDTO.getMerk());
        barang.setHargaBarang(createBarangRequestDTO.getHargaBarang());


        barangService.saveBarang(barang);

        model.addAttribute("sku", barang.getSku());
        model.addAttribute("tipeBarang", barang.getTipeBarang());
        model.addAttribute("merk", barang.getMerk());
        model.addAttribute("harga", barang.getHargaBarang());

        return "success-create-barang";
    }

    @GetMapping("barang/{id}")
    public String detailBarang(@PathVariable("id") String id, Model model) {
        var barang = barangService.getBarangById(id);
        var gudangBarang = gudangBarangService.findGudangByBarang(barang);
        var readBarangDTO = barangMapper.barangToReadBarangDTO(barang);

        int stok = barangService.findStok(barang);
        String tipeBarang = barangService.getTipeBarang(barang);

        readBarangDTO.setStok(stok);
        readBarangDTO.setTipeBarang(tipeBarang);

        model.addAttribute("barang", readBarangDTO);
        model.addAttribute("gudangBarang", gudangBarang);

        return "view-detail-barang";
    }

    @GetMapping("barang/{id}/ubah")
    public String formUpdatebarang(@PathVariable("id") String id, Model model) {
        var barang = barangService.getBarangById(id);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "form-update-barang";
    }

    @PostMapping("barang/ubah")
    public String updateBarang(@ModelAttribute UpdateBarangRequestDTO barangDTO, Model model) {
        var barangFromDto = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.updateBarang(barangFromDto);

        model.addAttribute("merk", barang.getMerk());
        model.addAttribute("harga", barang.getHargaBarang());

        return "success-update-barang";
    }
}
