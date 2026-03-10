package com.example.ktp.service.impl;

import com.example.ktp.mapper.KtpMapper;
import com.example.ktp.model.dto.KtpAddRequest;
import com.example.ktp.model.dto.KtpDto;
import com.example.ktp.model.entity.Ktp;
import com.example.ktp.repository.KtpRepository;
import com.example.ktp.service.KtpService;
import com.example.ktp.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto AddKtp(KtpAddRequest request) {
        validationUtil.validate(request);

        Ktp saveKtp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(saveKtp);

        KtpDto ktpDto = KtpMapper.MAPPER.toKtpDtoData(saveKtp);
        return ktpDto;
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktps = ktpRepository.findAll();
        List<KtpDto> ktpDto = new ArrayList<>();
        for (Ktp ktp : ktps) {
            ktpDto.add(KtpMapper.MAPPER.toKtpDtoData(ktp));
        }
        return ktpDto;
    }

    @Override
    public KtpDto getKtpById(int id) {
        Ktp ktp = ktpRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("KTP not found"));
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public KtpDto updateKtp(int id, KtpAddRequest request) {
        validationUtil.validate(request);

        Ktp existingKtp = ktpRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("KTP not found"));
        Ktp ktp = Ktp.builder()
                .id(existingKtp.getId())
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public void DeleteKtp(int id) {
        Ktp ktp = ktpRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("KTP not found"));
        ktpRepository.delete(ktp);
    }
}
