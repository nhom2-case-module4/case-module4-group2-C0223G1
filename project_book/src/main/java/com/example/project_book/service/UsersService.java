package com.example.project_book.service;



import com.example.project_book.model.User;
import com.example.project_book.repo.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService {
    @Autowired
    private IUsersRepo usersRepo;
//    @Override
//    public List<Users> getListUsers() {
//        return usersRepo.findAll();
//    }

    @Override
    public void add(User users) {
        usersRepo.save(users);
    }

    @Override
    public Page<User> findAllByIsDeleteIsFalse(Pageable pageable) {
        return usersRepo.findAllByIsDeleteIsFalse(pageable);
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < usersRepo.findAll().size(); i++) {
            if(usersRepo.findAll().get(i).getIdUser()==id){
                return usersRepo.findAll().get(i);
            }
        }
        return null;
    }

    @Override
    public void edit(User users) {
        usersRepo.save(users);
    }

    @Override
    public void delete(User users) {
        usersRepo.delete(users);
    }

    @Override
    public Page<User> findOne(String name, Pageable pageable) {
        return this.usersRepo.searchByName(name,pageable);
    }

    @Override
    public void deleteById(int id) {
        usersRepo.deleteById(id);
    }
}
