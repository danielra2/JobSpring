package mycode.jobspring.masina.dtos;
// Importurile necesare (ex: jakarta.validation)

public record MasinaDto(
        String marca,
        String model,
        int numarKilometri
) {}