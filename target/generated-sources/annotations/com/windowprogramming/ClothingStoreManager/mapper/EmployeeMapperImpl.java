package com.windowprogramming.ClothingStoreManager.mapper;

import com.windowprogramming.ClothingStoreManager.dto.request.employee.EmployeeCreationRequest;
import com.windowprogramming.ClothingStoreManager.dto.request.employee.EmployeeUpdateRequest;
import com.windowprogramming.ClothingStoreManager.dto.response.EmployeeResponse;
import com.windowprogramming.ClothingStoreManager.entity.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Ubuntu)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEmployee(EmployeeCreationRequest employeeCreationRequest) {
        if ( employeeCreationRequest == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.name( employeeCreationRequest.getName() );
        employee.citizenId( employeeCreationRequest.getCitizenId() );
        employee.jobTitle( employeeCreationRequest.getJobTitle() );
        employee.salary( employeeCreationRequest.getSalary() );
        employee.phoneNumber( employeeCreationRequest.getPhoneNumber() );
        employee.email( employeeCreationRequest.getEmail() );
        employee.dateOfBirth( employeeCreationRequest.getDateOfBirth() );
        employee.address( employeeCreationRequest.getAddress() );
        employee.area( employeeCreationRequest.getArea() );
        employee.ward( employeeCreationRequest.getWard() );

        return employee.build();
    }

    @Override
    public EmployeeResponse toEmployeeResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponse.EmployeeResponseBuilder employeeResponse = EmployeeResponse.builder();

        employeeResponse.id( employee.getId() );
        employeeResponse.name( employee.getName() );
        employeeResponse.phoneNumber( employee.getPhoneNumber() );
        employeeResponse.citizenId( employee.getCitizenId() );
        employeeResponse.jobTitle( employee.getJobTitle() );
        employeeResponse.salary( employee.getSalary() );
        employeeResponse.email( employee.getEmail() );
        employeeResponse.dateOfBirth( employee.getDateOfBirth() );
        employeeResponse.address( employee.getAddress() );
        employeeResponse.area( employee.getArea() );
        employeeResponse.ward( employee.getWard() );
        employeeResponse.employmentStatus( employee.getEmploymentStatus() );

        return employeeResponse.build();
    }

    @Override
    public void updateEmployee(Employee employee, EmployeeUpdateRequest employeeUpdateRequest) {
        if ( employeeUpdateRequest == null ) {
            return;
        }

        if ( employeeUpdateRequest.getId() != null ) {
            employee.setId( employeeUpdateRequest.getId() );
        }
        if ( employeeUpdateRequest.getName() != null ) {
            employee.setName( employeeUpdateRequest.getName() );
        }
        if ( employeeUpdateRequest.getCitizenId() != null ) {
            employee.setCitizenId( employeeUpdateRequest.getCitizenId() );
        }
        if ( employeeUpdateRequest.getJobTitle() != null ) {
            employee.setJobTitle( employeeUpdateRequest.getJobTitle() );
        }
        if ( employeeUpdateRequest.getSalary() != null ) {
            employee.setSalary( employeeUpdateRequest.getSalary() );
        }
        if ( employeeUpdateRequest.getPhoneNumber() != null ) {
            employee.setPhoneNumber( employeeUpdateRequest.getPhoneNumber() );
        }
        if ( employeeUpdateRequest.getEmail() != null ) {
            employee.setEmail( employeeUpdateRequest.getEmail() );
        }
        if ( employeeUpdateRequest.getDateOfBirth() != null ) {
            employee.setDateOfBirth( employeeUpdateRequest.getDateOfBirth() );
        }
        if ( employeeUpdateRequest.getAddress() != null ) {
            employee.setAddress( employeeUpdateRequest.getAddress() );
        }
        if ( employeeUpdateRequest.getArea() != null ) {
            employee.setArea( employeeUpdateRequest.getArea() );
        }
        if ( employeeUpdateRequest.getWard() != null ) {
            employee.setWard( employeeUpdateRequest.getWard() );
        }
    }
}
