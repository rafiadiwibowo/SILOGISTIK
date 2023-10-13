package apap.ti.silogistik2106653855;

import apap.ti.silogistik2106653855.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106653855.dto.response.BarangMapper;
import apap.ti.silogistik2106653855.dto.response.GudangMapper;
import apap.ti.silogistik2106653855.dto.response.KaryawanMapper;
import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.service.BarangService;
import apap.ti.silogistik2106653855.service.GudangService;
import apap.ti.silogistik2106653855.service.KaryawanService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Locale;

@SpringBootApplication
public class Silogistik2106653855Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106653855Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService , GudangMapper gudangMapper, KaryawanMapper karyawanMapper){
		return args ->{
			var faker = new Faker(new Locale("in-ID"));
				var gudangDTO = new CreateGudangRequestDTO();
				gudangDTO.setNama(faker.company().name());
				gudangDTO.setAlamatGudang(faker.address().fullAddress());

				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
				gudangService.createGudang(gudang);

			var karyawanDTO = new CreateKaryawanRequestDTO();

			karyawanDTO.setNama(faker.name().fullName());
			karyawanDTO.setTanggalLahir(faker.date().birthday());
			karyawanDTO.setJenisKelamin(faker.number().numberBetween(1,2));


			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawanService.saveKaryawan(karyawan);
		};
	}

}
