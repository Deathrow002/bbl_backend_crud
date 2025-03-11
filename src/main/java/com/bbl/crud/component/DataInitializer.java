package com.bbl.crud;

import com.bbl.crud.model.UserModel;
import com.bbl.crud.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the database is empty, to avoid duplicate entries on every app start
        if (userRepository.count() == 0) {
            userRepository.saveAll(getDefaultUsers());
        }
    }

    private Iterable<UserModel> getDefaultUsers() {
        return Arrays.asList(
                new UserModel(1, "Leanne Graham", "Bret", "Sincere@april.biz", "1-770-736-8031 x56442", "hildegard.org",
                        new UserModel.Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new UserModel.Geo("-37.3159", "81.1496")),
                        new UserModel.Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets")),
                new UserModel(2, "Ervin Howell", "Antonette", "Shanna@melissa.tv", "010-692-6593 x09125", "anastasia.net",
                        new UserModel.Address("Victor Plains", "Suite 879", "Wisokyburgh", "90566-7771", new UserModel.Geo("-43.9509", "-34.4618")),
                        new UserModel.Company("Deckow-Crist", "Proactive didactic contingency", "synergize scalable supply-chains")),
                new UserModel(3, "Clementine Bauch", "Samantha", "Nathan@yesenia.net", "1-463-123-4447", "ramiro.info",
                        new UserModel.Address("Douglas Extension", "Suite 847", "McKenziehaven", "59590-4157", new UserModel.Geo("-68.6102", "-47.0653")),
                        new UserModel.Company("Romaguera-Jacobson", "Face to face bifurcated interface", "e-enable strategic applications")),
                new UserModel(4, "Patricia Lebsack", "Karianne", "Julianne.OConner@kory.org", "493-170-9623 x156", "kale.biz",
                        new UserModel.Address("Hoeger Mall", "Apt. 692", "South Elvis", "53919-4257", new UserModel.Geo("29.4572", "-164.2990")),
                        new UserModel.Company("Robel-Corkery", "Multi-tiered zero tolerance productivity", "transition cutting-edge web services")),
                new UserModel(5, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca", "(254)954-1289", "demarco.info",
                        new UserModel.Address("Skiles Walks", "Suite 351", "Roscoeview", "33263", new UserModel.Geo("-31.8129", "62.5342")),
                        new UserModel.Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems")),
                new UserModel(6, "Mrs. Dennis Schulist", "Leopoldo_Corkery", "Karley_Dach@jasper.info", "1-477-935-8478 x6430", "ola.org",
                        new UserModel.Address("Norberto Crossing", "Apt. 950", "South Christy", "23505-1337", new UserModel.Geo("-71.4197", "71.7478")),
                        new UserModel.Company("Considine-Lockman", "Synchronised bottom-line interface", "e-enable innovative applications")),
                new UserModel(7, "Kurtis Weissnat", "Elwyn.Skiles", "Telly.Hoeger@billy.biz", "210.067.6132", "elvis.io",
                        new UserModel.Address("Rex Trail", "Suite 280", "Howemouth", "58804-1099", new UserModel.Geo("24.8918", "21.8984")),
                        new UserModel.Company("Johns Group", "Configurable multimedia task-force", "generate enterprise e-tailers")),
                new UserModel(8, "Nicholas Runolfsdottir V", "Maxime_Nienow", "Sherwood@rosamond.me", "586.493.6943 x140", "jacynthe.com",
                        new UserModel.Address("Ellsworth Summit", "Suite 729", "Aliyaview", "45169", new UserModel.Geo("-14.3990", "-120.7677")),
                        new UserModel.Company("Abernathy Group", "Implemented secondary concept", "e-enable extensible e-tailers")),
                new UserModel(9, "Glenna Reichert", "Delphine", "Chaim_McDermott@dana.io", "(775)976-6794 x41206", "conrad.com",
                        new UserModel.Address("Dayna Park", "Suite 449", "Bartholomebury", "76495-3109", new UserModel.Geo("24.6463", "-168.8889")),
                        new UserModel.Company("Yost and Sons", "Switchable contextually-based project", "aggregate real-time technologies")),
                new UserModel(10, "Clementina DuBuque", "Moriah.Stanton", "Rey.Padberg@karina.biz", "024-648-3804", "ambrose.net",
                        new UserModel.Address("Kattie Turnpike", "Suite 198", "Lebsackbury", "31428-2261", new UserModel.Geo("-38.2386", "57.2232")),
                        new UserModel.Company("Hoeger LLC", "Centralized empowering task-force", "target end-to-end models"))
        );
    }
}
