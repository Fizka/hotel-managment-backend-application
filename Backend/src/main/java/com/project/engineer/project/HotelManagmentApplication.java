package com.project.engineer.project;

import com.project.engineer.project.repository.*;
import com.project.engineer.project.service.ReservetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class HotelManagmentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HotelManagmentApplication.class, args);
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    ResponsibilityRepository responsibilityRepository;

    @Autowired
    ReservetService reservetService;

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @Override
    public void run(String... args) throws Exception {
/*
        Faker faker = new Faker(new Locale("pl"));

        Customer customer;
        Reservation reservation;
        Room room;
        Worker worker;

        ArrayList<Customer> customerList = new ArrayList();
        ArrayList<Worker> workerList = new ArrayList();
        ArrayList<Room> roomList = new ArrayList();
        ArrayList<Reservation> reservationList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            Name name = faker.name();
            String firstname = name.firstName();
            String lastname = name.lastName();
            String login = name.username();
            String password = faker.internet().password();
            List<Integer> privileges = List.of(1, 1, 1, 1, 2, 2, 2, 2, 3);
            String email = faker.internet().emailAddress();
            customer = new Customer(firstname, lastname, login, password,
                    privileges.get(faker.number().numberBetween(0, privileges.size())), email);
            customerRepository.save(customer);
            customerList.add(customer);
        }


        for (int i = 0; i < 10; i++) {
            Name name = faker.name();
            String firstname = name.firstName();
            String lastname = name.lastName();
            String login = name.username();
            String password = faker.internet().password();
            List<Integer> privileges = List.of(1, 1, 1, 1, 2, 2, 2, 2, 3);
            String email = faker.internet().emailAddress();
            String pesel = faker.number().digits(11);
            List<String> position = List.of("Recepcja", "Sprzątanacz/ka", "kucharz/rka", "Lokaj", "Kelner/ka");
            List<String> responsibilities = List.of("Sprzątanie pokoju 202", "Sprzątanie pokoju 302", "Stanie na recepcji", "Kelnerowanie", "Gotowanie", "Ten pracownik nie ma przzypisanych obowiążków");
            Date dateofbirth = faker.date().birthday(18, 90);
            Date dateofemployment = faker.date().past(3600, TimeUnit.DAYS);
            int salary = faker.number().numberBetween(3000, 20000);
            List<String> activity = List.of("Pracujący", "Na urlopie", "Poza pracą");
            List<String> workinghours = List.of("9:00-17:00", "14:00-22:00", "21:00-5:00");

            worker = new Worker(firstname, lastname, login, password,
                    email, responsibilities.get(faker.number().numberBetween(0, responsibilities.size())),
                    position.get(faker.number().numberBetween(0, position.size())),
                    dateofemployment, dateofbirth, salary, activity.get(faker.number().numberBetween(0, activity.size())),
                    pesel, workinghours.get(faker.number().numberBetween(0, workinghours.size())),
                    privileges.get(faker.number().numberBetween(0, privileges.size())));

            workerRepository.save(worker);
            workerList.add(worker);
        }

        for (int i = 0; i < 50; i++) {
            int random = (int) Math.floor(Math.random() * 83);
            String urlImages = "" + random + ".png";
            int floor = faker.number().numberBetween(1, 10);
            int numberRM = faker.number().numberBetween(1, 100);
            int price = faker.number().numberBetween(100, 7000);
            String description = faker.hitchhikersGuideToTheGalaxy().quote();
            int macCapcity = faker.number().numberBetween(1, 10);
            String title = faker.yoda().quote();

            room = new Room(urlImages, floor, numberRM, price, description, macCapcity, title);
            roomRepository.save(room);
            roomList.add(room);
        }


        for (int i = 0; i < 50; i++) {
            Date dataStart = faker.date().future(360, TimeUnit.DAYS);
            LocalDate ld = convertToLocalDateViaSqlDate(dataStart);
            LocalDate dateend = ld.plus(faker.number().numberBetween(1, 21), ChronoUnit.DAYS);
            List<Boolean> status = List.of(true, false);
            long idd = 111;
            Optional<Room> _room = roomRepository.findById(idd);
            reservation = new Reservation(status.get(faker.number().numberBetween(0, 1)), ld, dateend, faker.number().numberBetween(1, 10), _room.get(), customerList.get(faker.number().numberBetween(0, 9)));
            reservation.setRoom("Piętro: " + _room.get().getFloor() + " Numer: " + _room.get().getNumberRM());
            reservation.setCustomer(customerList.get(faker.number().numberBetween(0, 9)).getFirstname() + " " + customerList.get(faker.number().numberBetween(0, 9)).getLastname());
            reservationRepository.save(reservation);
            reservetService.setReservation(reservation);
        }



        for (int i = 0; i < 100; i++) {
            List<String> responsibilities = List.of("Sprzątanie pokoju 202", "Sprzątanie pokoju 302", "Stanie na recepcji", "Kelnerowanie", "Gotowanie", "Mycie", "Pakowanie");
            List<String> status = List.of("Wykonane", "Do zrobienbia", "W trakcie");
            Worker newWorker = workerList.get(faker.number().numberBetween(0, workerList.size()));
            Responsibility responsibility = new Responsibility(
                    responsibilities.get(faker.number().numberBetween(0, responsibilities.size())),
                    "Brak szczegółów", newWorker.getFirstname() + " " + newWorker.getLastname(),
                    status.get(faker.number().numberBetween(0, status.size())),
                    newWorker.getidWorker());
            responsibilityRepository.save(responsibility);
        }
*/
    }
}

