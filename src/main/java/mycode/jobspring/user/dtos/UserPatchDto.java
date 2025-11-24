package mycode.jobspring.user.dtos;

import mycode.jobspring.masina.dtos.MasinaDto;

import java.util.Set;

public record UserPatchDto(
        String nume,
        String prenume,
        Integer varsta,
        Set<MasinaDto> masini
) {
}
