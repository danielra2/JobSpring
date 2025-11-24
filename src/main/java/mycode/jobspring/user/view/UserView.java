package mycode.jobspring.user.view;

import mycode.jobspring.masina.dtos.MasinaDto;
import mycode.jobspring.masina.dtos.MasinaListResponse;
import mycode.jobspring.masina.service.MasinaQuerryService;
import mycode.jobspring.user.dtos.UserDto;
import mycode.jobspring.user.dtos.UserListResponse;
import mycode.jobspring.user.dtos.UserResponse;
import mycode.jobspring.user.exceptions.UserAlreadyExistsException;
import mycode.jobspring.user.exceptions.UserDoesntExistException;
import mycode.jobspring.user.repository.UserRepository;
import mycode.jobspring.user.service.UserCommandService;
import mycode.jobspring.user.service.UserQueryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;

@Component
public class UserView {
    private UserQueryService userQueryService;
    private UserCommandService userCommandService;
    private MasinaQuerryService masinaQuerryService; // Nou
    private final Scanner scanner;
    private String prenume;

    // Constructorul modificat pentru a injecta noul Service
    public UserView(UserCommandService userCommandService, UserQueryService userQueryService, UserRepository userRepository, MasinaQuerryService masinaQuerryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.masinaQuerryService = masinaQuerryService; // Setarea noului service
        this.scanner = new Scanner(System.in);
        this.prenume = prenume;
    }

    public void menu() {

        System.out.println("1 -> Adauga User si Masina");
        System.out.println("2->Sterge user");
        System.out.println("3->Afiseaza userii");
        System.out.println("4->Afiseaza masinile");
        System.out.println("5->Adauga o masina la user");
        System.out.println("6->Update user PUT");
        System.out.print("Alege o optiune: ");
    }

    public void play() {
        boolean merge = true;
        while (merge) {
            this.menu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1:
                        viewAddUser();
                        break;
                    case 2:
                        viewDeleteUserByNumePrenume();
                        break;
                    case 3:
                        viewFindAllUsers();
                        break;
                    case 4:
                        viewFindAllMasini();
                        break;
                    case 5:
                        viewAddMasinaToUser();
                        break;
                    case 6:
                        viewUpdateUserPut();
                        break;
                    case 0:
                        merge = false;
                        System.out.println("Iesire din aplicatie.");
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }
    }

    private void printUserList(UserListResponse listResponse) {
        if (listResponse == null || listResponse.userList().isEmpty()) {
            System.out.println("Nu s-a gasit niciun user.");
            return;
        }
        listResponse.userList().forEach(user -> {
            System.out.println("ID: " + user.id() + ", Nume: " + user.nume() + " " + user.prenume() + ", Varsta: " + user.varsta());
            if (!user.masini().isEmpty()) {
                System.out.println("  Masini:");
                user.masini().forEach(masina -> {
                    System.out.println("    - Marca: " + masina.marca() + ", Model: " + masina.model() + ", KM: " + masina.numarKilometri());
                });
            } else {
                System.out.println("  Nu are masini.");
            }
        });
    }


    public void viewAddUser() {
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Varsta: ");
        int varsta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Adauga Masina");
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Numar Kilometri: ");
        int km = scanner.nextInt();
        scanner.nextLine();

        MasinaDto masinaDto = new MasinaDto(marca, model, km);
        Set<MasinaDto> masini = Set.of(masinaDto);

        UserDto userDto = new UserDto(nume, prenume, varsta, masini);

        try {
            userCommandService.createUser(userDto);
            System.out.println("Userul " + nume + " a fost adaugat cu masina.");
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    public void viewDeleteUserByNumePrenume() {
        System.out.print("Introduceti Numele");
        String nume = scanner.nextLine();
        System.out.print("Introduceti Prenumele ");
        String prenume = scanner.nextLine();

        try {
            UserResponse deletedUser = userCommandService.deleteUserByNumeAndPrenume(nume, prenume);
            System.out.println(" Utilizatorul " + deletedUser.nume() + " " + deletedUser.prenume() + " a fost sters.");
        } catch (RuntimeException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    public void viewFindAllUsers() {
        System.out.println(" Afiseaza Toti Userii");
        UserListResponse response = userQueryService.findAllUsers();
        printUserList(response);
    }

    public void viewFindAllMasini() {
        System.out.println("Afiseaza Toate Masinile");
        MasinaListResponse response = masinaQuerryService.findAllMasini();
        if (response.masiniList().isEmpty()) {
            System.out.println("Nu s-a gasit nicio masina.");
            return;
        }
        response.masiniList().forEach(masina -> {
            System.out.println("ID: " + masina.id() + ", Marca: " + masina.marca() + ", Model: " + masina.model());
        });
    }
    public void viewAddMasinaToUser(){
        System.out.println("Adauga Masina la User");
        System.out.print("Introduceti ID-ul utilizatorului: ");
        long userId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Marca Masinii: ");
        String marca = scanner.nextLine();
        System.out.print("Model Masinii: ");
        String model = scanner.nextLine();
        System.out.print("Numar Kilometri: ");
        int km = scanner.nextInt();
        scanner.nextLine();

        MasinaDto masinaDto = new MasinaDto(marca, model, km);

        try{
            UserResponse updatedUser = userCommandService.addMasinaToUser(userId, masinaDto);
            System.out.println("Masina " +marca+ " a fost adaugata la Userul: "+updatedUser.nume());
        } catch (UserDoesntExistException e) {
            e.printStackTrace();
        }
    }
    public void viewUpdateUserPut(){
        System.out.println("Introduceti id-ul userului");
        int id= scanner.nextInt();
        System.out.println("Nume nou: ");
        String nume=scanner.nextLine();
        System.out.println("Prenume nou: ");
        String prenume=scanner.nextLine();
        System.out.println("Varsta noua ");
        int varsta=scanner.nextInt();
        scanner.nextLine();
        try{
            UserDto userDto=new UserDto(nume,prenume,varsta,Set.of());
            UserResponse updatedUser=userCommandService.updateUserPut(id,userDto);
            System.out.println("Userul cu id: "+id+ " a fost actualizat cu succes noul nume este: "+nume);

        }catch (UserDoesntExistException e){
            e.printStackTrace();
        }

    }
}