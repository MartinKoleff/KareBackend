package com.koleff.kare.models.dto;

import com.koleff.kare.models.entity.User;


public record LoginResponseDTO (
     User user,
     String jwt
){}
