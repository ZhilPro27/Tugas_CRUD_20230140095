package com.example.ktp.service;


import com.example.ktp.model.dto.KtpAddRequest;
import com.example.ktp.model.dto.KtpDto;

import java.util.List;

public interface KtpService {
    KtpDto AddKtp(KtpAddRequest request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(int id);
    KtpDto updateKtp(int id, KtpAddRequest request);
    void DeleteKtp(int id);
}
