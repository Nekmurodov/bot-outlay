package org.example.botoutlay.dbConfig.util;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.Currency;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.example.botoutlay.dbConfig.entityAndService.enums.Permissions;
import org.example.botoutlay.dbConfig.entityAndService.enums.Role;
import org.example.botoutlay.dbConfig.entityAndService.repository.CurrencyRepository;
import org.example.botoutlay.dbConfig.entityAndService.repository.UserRepository;
import org.example.botoutlay.dbConfig.factory.UserFactorySingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrencyRepository currencyRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            Currency usd = new Currency();
            usd.setCcy(CurrencyType.USD);
            currencyRepository.save(usd);

            Currency uzs = new Currency();
            uzs.setCcy(CurrencyType.UZS);
            currencyRepository.save(uzs);

            Currency rub = new Currency();
            rub.setCcy(CurrencyType.RUB);
            currencyRepository.save(rub);

            Currency eur = new Currency();
            eur.setCcy(CurrencyType.EUR);
            currencyRepository.save(eur);

            UserFactorySingleton instance = UserFactorySingleton.getInstance();

            userRepository.save(instance.createUser(
                    "admin",
                    "Admin",
                    "admin",
                    passwordEncoder.encode("123"),
                    Role.ADMIN,
                    Arrays.stream(Permissions.values()).toList()));


            userRepository.save(instance.createUser(
                    "Owner",
                    "Owner",
                    "Owner",
                    passwordEncoder.encode("321"),
                    Role.OWNER,
                    Arrays.stream(Permissions.values()).toList()));

        }
    }
}
