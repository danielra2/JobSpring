package mycode.jobspring.mappers;

import mycode.jobspring.masina.dtos.MasinaDto;
import mycode.jobspring.masina.dtos.MasinaResponse;
import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserNumePrenumeVarstaResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.dtos.UserSimpleResponse;
import mycode.jobspring.user.models.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JobSpringMapper {

    // --- Utilitare ---
    private static String nvl(String s) { return s == null ? "" : s; }
    private static String trim(String s) { return s == null ? null : s.trim(); }

    // -----------------------------------------------------------
    // --- MAPPERS PENTRU MASINA ---
    // -----------------------------------------------------------

    public MasinaResponse mapMasinaToMasinaResponse(Masina e) {
        Objects.requireNonNull(e, "Masina entity is null");
        return new MasinaResponse(
                e.getId(),
                nvl(e.getMarca()),
                nvl(e.getModel()),
                e.getNumarKilometri()
        );
    }

    public Masina mapMasinaDtoToMasina(MasinaDto dto) {
        Objects.requireNonNull(dto, "Masina DTO is null");
        Masina e = new Masina();
        e.setMarca(trim(dto.marca()));
        e.setModel(trim(dto.model()));
        e.setNumarKilometri(dto.numarKilometri());
        // User-ul va fi setat în Service
        return e;
    }

    public Set<MasinaResponse> mapMasiniToMasinaResponseSet(Set<Masina> masini) {
        if (masini == null) return Set.of();
        // Folosim TreeSet deoarece entitatea User folosește TreeSet
        return masini.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToMasinaResponse)
                .collect(Collectors.toSet()); // Folosim toSet pentru output
    }

    public Set<Masina> mapMasinaDtoSetToMasinaSet(Set<MasinaDto> masiniDto) {
        if (masiniDto == null) return new TreeSet<>();
        return masiniDto.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaDtoToMasina)
                .collect(Collectors.toCollection(HashSet::new));
    }

    // -----------------------------------------------------------
    // --- MAPPERS PENTRU USER ---
    // -----------------------------------------------------------

    public UserResponse mapUserToUserResponse(User u) {
        Objects.requireNonNull(u, "User entity is null");

        Set<MasinaResponse> masiniResponse = mapMasiniToMasinaResponseSet(u.getMasini());

        return new UserResponse(
                u.getId(),
                nvl(u.getNume()),
                nvl(u.getPrenume()),
                u.getVarsta(),
                masiniResponse
        );
    }

    public User mapUserDtoToUser(UserDto dto) {
        Objects.requireNonNull(dto, "User DTO is null");
        User u = new User();
        u.setNume(trim(dto.nume()));
        u.setPrenume(trim(dto.prenume()));
        u.setVarsta(dto.varsta());
        // Mașinile sunt inițializate ca Set gol în constructorul User()
        return u;
    }

    public List<UserResponse> mapUserListToUserResponseList(List<User> userList) {
        if (userList == null) return List.of();
        return userList.stream().map(this::mapUserToUserResponse).toList();
    }
    public List<MasinaResponse> mapMasinaListToMasinaResponseList(List<Masina> masini) {
        if (masini == null) return List.of();
        return masini.stream()
                .filter(Objects::nonNull)
                .map(this::mapMasinaToMasinaResponse)
                .toList();
    }
    public UserNumePrenumeVarstaResponse mapUserToNumePrenumeVarstaResponse(User u){
        Objects.requireNonNull(u,"User entity is null");
        return new UserNumePrenumeVarstaResponse(nvl(u.getNume()),nvl(u.getPrenume()),u.getVarsta());
    }
    public List<UserNumePrenumeVarstaResponse> mapUserListToNumePrenumeVarstaResponseList(List<User> list){
        if(list==null)return List.of();
        return list.stream().filter(Objects::nonNull).map(this::mapUserToNumePrenumeVarstaResponse).toList();
    }

    // NOU: Mapeaza User la UserSimpleResponse
    public UserSimpleResponse mapUserToSimpleResponse(User u){
        Objects.requireNonNull(u,"User entity is null");
        return new UserSimpleResponse(nvl(u.getNume()),nvl(u.getPrenume()));
    }
    public List<UserSimpleResponse> mapUserListToSimpleResponseList(List<User> list){
        if(list==null)return List.of();
        return list.stream().filter(Objects::nonNull).map(this::mapUserToSimpleResponse).toList();
    }
}