package mycode.jobspring.user.dtos;
import java.util.Set;
import mycode.jobspring.masina.dtos.MasinaResponse; // Import nou

public record UserResponse(
        Long id,
        String nume,
        String prenume,
        int varsta,
        Set<MasinaResponse> masini // Lista de DTO-uri de Răspuns de Mașini
) {}