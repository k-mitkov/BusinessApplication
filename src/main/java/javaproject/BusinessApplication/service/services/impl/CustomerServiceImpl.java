package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Customer;
import javaproject.BusinessApplication.data.repositories.CustomerRepository;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.service.services.CustomerService;
import javaproject.BusinessApplication.web.models.customer.CustomerAddressModel;
import javaproject.BusinessApplication.web.models.customer.CustomerPhoneModel;
import javaproject.BusinessApplication.web.models.customer.CustomerRegisterModel;
import javaproject.BusinessApplication.web.models.customer.CustomerSearchModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,ModelMapper modelMapper) {
        this.modelMapper=modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(CustomerRegisterModel customerRegisterModel) {
        if (customerRepository.existsByName(customerRegisterModel.getName())){
            throw new EntityAlreadyExistsException(String.format(
                    "Customer with name '%s' already exists",customerRegisterModel.getName()));
        }
        Customer customer=modelMapper.map(customerRegisterModel,Customer.class);
        this.save(customer);
        return customer;
    }

    @Override
    public String getAllCustomers() {
        return customerRepository.findAll().stream().map(Customer::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Customer updateAddress(CustomerAddressModel customerAddressModel) {
        Customer customer=this.getCustomer(customerAddressModel.getName());
        customer.setAddress(customerAddressModel.getAddress());
        this.save(customer);
        return customer;
    }

    @Override
    public Customer updatePhoneNumber(CustomerPhoneModel customerPhoneModel) {
        Customer customer=this.getCustomer(customerPhoneModel.getName());
        customer.setPhoneNumber(customerPhoneModel.getPhoneNumber());
        this.save(customer);
        return customer;
    }

    @Override
    public Customer updateAddressAndPhoneNumber(CustomerRegisterModel customerRegisterModel) {
        Customer customer=this.getCustomer(customerRegisterModel.getName());
        customer.setAddress(customerRegisterModel.getAddress());
        customer.setPhoneNumber(customerRegisterModel.getPhoneNumber());
        this.save(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer deleteCustomer(CustomerSearchModel customerSearchModel) {
        Customer customer=this.getCustomer(customerSearchModel.getName());
        customerRepository.deleteByName(customer.getName());
        return customer;
    }

    @Override
    public Customer getCustomer(String name){
        return customerRepository.findByName(name)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("Not found customer with name '%s'",name)));
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
