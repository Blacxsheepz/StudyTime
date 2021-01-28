package com.cpe.backend;


import com.cpe.backend.entity.Company;
import com.cpe.backend.entity.Position;
import com.cpe.backend.entity.Benefit;



import com.cpe.backend.repository.CompanyRepository;
import com.cpe.backend.repository.PositionRepository;
import com.cpe.backend.repository.BenefitRepository;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CompanyRepository companyRepository, PositionRepository positionRepository, BenefitRepository benefitRepository) {
		return args -> {
			Stream.of("บริษัทบะหมี่เกี๊ยวและก๋วยเตี๊ยวส่งออกที่ใหญ่ที่สุดของซีกโลกตะวันออกของคุณตอง").forEach(name -> {
				Company company = new Company(); 
				company.setName(name); 
				companyRepository.save(company); 
			});

			Stream.of("ผู้จัดการฝ่ายบัญชี","พนักงานบัญชี", "หัวหน้าฝ่ายบัญชี","เจ้าหน้าที่ฝ่ายธุรการ", "ผู้จัดการฝ่ายธุรการ","แอร์ โอสเตส",
			"สถาปนิก",
			"ผู้กำกับฝ่ายศิลป์",
			"วิศวกรทางด้านภาพและเสียง" ,
			"ผู้ตรวจสอบบัญชี",
			"พนักงานรับจ่ายเงิน",
			"พนักงานเก็บบิล/เงิน",
			"นายหน้าค้าหุ้นและพันธบัตร",
			"ผู้จัดการฝ่ายงบประมาณ",
			"นักวิเคราะห์ทางธุรกิจ",
			"ที่ปรึกษาทางธุรกิจ",
			"ผู้บริหารระดับสูง",
			"วิศวกรโยธา",
			"ช่างเทคนิคทางด้านสี",
			"เจ้าหน้าที่ฝ่ายสินเชื่อธุรกิจ",
			"ผู้ออกแบบภาพเคลื่อนไหว",
			"วิศวกรด้านการออกแบบ",
			"วิศวกรไฟฟ้า",
			"นักออกแบบเสื้อผ้า",
			"พนักงานทำความสะอาด",
			"วิศวกรอุตสาหกรรม").forEach(name -> {
				Position position = new Position(); 
				position.setName(name); 
				positionRepository.save(position); 
			});

			Stream.of("ค่าน้ำมันรถ (ในพื้นที่, นอกพื้นที่)","ค่าโทรศัพท์","ประกันชีวิตพนักงานรายวัน,รายเดือน","โบนัส และ การปรับค่าจ้างประจำปี","กองทุนเงินทดแทน","รถส่วนตัว","ห้องพยาบาล","ตรวจสุขภาพประจำปี").forEach(name -> {
				Benefit benefit = new Benefit(); 
				benefit.setName(name); 
				benefitRepository.save(benefit); 
			});
			
			companyRepository.findAll().forEach(System.out::println); 
			positionRepository.findAll().forEach(System.out::println);
			benefitRepository.findAll().forEach(System.out::println); 
		};
	}

}
