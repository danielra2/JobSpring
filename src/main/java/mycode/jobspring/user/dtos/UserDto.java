package mycode.jobspring.user.dtos;

import java.util.Set;
import mycode.jobspring.masina.dtos.MasinaDto;

public record UserDto(
        String nume,
        String prenume,
        int varsta,
        Set<MasinaDto> masini
) {}