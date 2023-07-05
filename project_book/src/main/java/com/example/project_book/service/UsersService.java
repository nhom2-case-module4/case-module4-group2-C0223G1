package com.example.project_book.service;



import com.example.project_book.model.Users;
import com.example.project_book.repo.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsersService implements IUsersService {
    @Autowired
    private IUsersRepo usersRepo;
//    @Override
//    public List<Users> getListUsers() {
//        return usersRepo.findAll();
//    }

    @Override
    public void add(Users users) {
        usersRepo.save(users);
    }

    @Override
    public Page<Users> findAllByFlagDeleteIsFalse(Pageable pageable) {
        return usersRepo.findAllByFlagDeleteIsFalse(pageable);
    }

    @Override
    public Users findById(int id) {
        for (int i = 0; i < usersRepo.findAll().size(); i++) {
            if(usersRepo.findAll().get(i).getId()==id){
                return usersRepo.findAll().get(i);
            }
        }
        return null;
    }

    @Override
    public void edit(Users users) {
        usersRepo.save(users);
    }

    @Override
    public void delete(Users users) {
        usersRepo.delete(users);
    }

    @Override
    public Page<Users> findOne(String name, Pageable pageable) {
        return this.usersRepo.searchByName(name,pageable);
    }
}
