package mycode.jobspring.masina.dtos;

public record MasinaResponse(
        Long id,
        String marca,
        String model,
        int numarKilometri
) {}