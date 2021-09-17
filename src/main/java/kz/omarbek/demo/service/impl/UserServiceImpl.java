package kz.omarbek.demo.service.impl;

import kz.omarbek.demo.service.IUserService;
import kz.omarbek.demo.shared.Utils;
import kz.omarbek.demo.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String FILE_NAME = "/Users/omarbekdinasil/Documents/projects/demo/src/main/resources/users.txt";

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(utils.generateUserId(30));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserDto user = findByEmail(userDto.getEmail());
        if (user == null) {
            appendStrToFile(FILE_NAME, userDto.toString());
        }

        return userDto;
    }

    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.newLine();
            out.close();
        } catch (IOException e) {
            System.out.println("exception occured" + e);
        }
    }

    @Override
    public UserDto getUser(String email) {
        return findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = findByEmail(email);
        if (userDto == null) {
            throw new UsernameNotFoundException(email);
        }

        User user = new User(userDto.getEmail(), userDto.getEncryptedPassword(), new ArrayList<>());
        return user;
    }

    private UserDto findByEmail(String email) {
        UserDto userDto = new UserDto();
        Scanner scan;
        String user = null;
        try {
            scan = new Scanner(new File(FILE_NAME));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.startsWith(email)) {
                    user = line;
                    break;
                }
            }
            if (user != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(user, " ");
                while (stringTokenizer.hasMoreElements()) {
                    userDto.setEmail(stringTokenizer.nextToken());
                    userDto.setUserId(stringTokenizer.nextToken());
                    userDto.setEncryptedPassword(stringTokenizer.nextToken());
                }
                return userDto;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
