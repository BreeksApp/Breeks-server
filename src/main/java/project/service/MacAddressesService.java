package project.service;

import project.entity.MacAddresses;

public interface MacAddressesService {
    boolean existsByAddress(String address);
    void addAddress(MacAddresses address);
    boolean deleteAddress(String address);
}
