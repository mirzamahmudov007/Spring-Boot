package uz.gayratjon.homework.service;

import org.springframework.stereotype.Service;
import uz.gayratjon.homework.entity.Employee;
import uz.gayratjon.homework.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id){
        // Employee employee = employeeRepository.findById(id).get();
        /* Ushbu yuqoridagi holatda db da mavjud bo'lmagan employee ning id si berilsa error (500) qayatariladi.
        Buning oldini olish uchun quyidagicha yozamiz
         */
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

/**********************************************************************************************************
 * Quyidagi 3 ta findAll method lar Repositorydagi 3 xil Query lar uchun yozilgan
 *
***********************************************************************************************************/
    /*
    public List<Employee> findAll1(String name, String lastName){
        List<Employee> employees = employeeRepository.findAll(name, lastName);
        return employees;
    }
    */
/*
    public List<Employee> findAll2(String name, String lastName, String emailEnding){
        List<Employee> employees = employeeRepository.findAllByNameAndAndLastNameAndEmailEndingWith(name,
                lastName, emailEnding);
        return employees;
    }
*/
    public List<Employee> findAll3(String name, String lastName){
        List<Employee> employees = employeeRepository.findAll(name, lastName);
        return employees;
    }

    /********************************************************************************
     *  Ixtiyoriy jumla (matn) qatnashgan so'z orqali qidirish                      *
     ********************************************************************************/

    public List<Employee> getSearch(String jumla){
        List<Employee> employees = employeeRepository.getSearch(jumla);
        return employees;
    }


    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
