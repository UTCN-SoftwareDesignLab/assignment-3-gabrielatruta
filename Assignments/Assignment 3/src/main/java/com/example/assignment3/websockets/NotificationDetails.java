package com.example.assignment3.websockets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDetails {

    private String patientName;
    private String dateOfConsult;

}
