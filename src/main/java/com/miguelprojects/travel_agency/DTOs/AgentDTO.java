package com.miguelprojects.travel_agency.DTOs;

import com.miguelprojects.travel_agency.Models.Manager;
import com.miguelprojects.travel_agency.Models.Reservation;
import com.miguelprojects.travel_agency.Models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AgentDTO extends User {

    private String specialization;

    private Long managerId;
}
