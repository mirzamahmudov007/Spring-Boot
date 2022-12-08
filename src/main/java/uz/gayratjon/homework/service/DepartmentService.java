package uz.gayratjon.homework.service;

import org.springframework.stereotype.Service;
import uz.gayratjon.homework.entity.Department;
import uz.gayratjon.homework.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department){
        Department result = departmentRepository.save(department);
        return result;
    }

    public Department getOne(Long id){
        Department result = departmentRepository.findById(id).get();
        return result;
    }

    public List<Department> getAll(){
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public String delete(Long id){
        if (departmentRepository.findById(id).isPresent()){
            departmentRepository.deleteById(id);
            return "Deleted successfully";
        }
        return "Department Not Found !!!";

    }
}
