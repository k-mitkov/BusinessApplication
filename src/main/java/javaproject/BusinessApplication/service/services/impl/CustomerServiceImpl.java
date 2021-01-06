package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Customer;
import javaproject.BusinessApplication.data.entities.Merchant;
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
    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository customerRepository,ModelMapper modelMapper) {
        this.modelMapper=modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer addCustomer(CustomerRegisterModel customerRegisterModel) {
        if (customerRepository.existsByName(customerRegisterModel.getName())){
            throw new EntityAlreadyExistsException(String.format(
                    "Customer with name '%s' already exists",customerRegisterModel.getName()));
        }
        Customer customer=modelMapper.map(customerRegisterModel,Customer.class);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public String getAllCustomers() {
        return customerRepository.findAll().stream().map(Customer::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Customer updateAddress(CustomerAddressModel customerAddressModel) {
        Customer customer=customerRepository.findByName(customerAddressModel.getName())
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("Not found customer with type '%s'",customerAddressModel.getName())));
        customer.setAddress(customerAddressModel.getAddress());
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    @Override
    public Customer updatePhoneNumber(CustomerPhoneModel customerPhoneModel) {
        Customer customer=customerRepository.findByName(customerPhoneModel.getName())
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("Not found customer with type '%s'",customerPhoneModel.getName())));
        customer.setPhoneNumber(customerPhoneModel.getPhoneNumber());
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    @Override
    public Customer updateAddressAndPhoneNumber(CustomerRegisterModel customerRegisterModel) {
        Customer customer=customerRepository.findByName(customerRegisterModel.getName())
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("Not found customer with type '%s'",customerRegisterModel.getName())));
        customer.setAddress(customerRegisterModel.getAddress());
        customer.setPhoneNumber(customerRegisterModel.getPhoneNumber());
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer deleteCustomer(CustomerSearchModel customerSearchModel) {
        Customer customer=customerRepository.findByName(customerSearchModel.getName())
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("Not found customer with type '%s'",customerSearchModel.getName())));
        customerRepository.deleteByName(customer.getName());
        return customer;
    }
}
