package com.kenzie.searching.packagesearch;

import java.util.List;


/**
 * Manages a list of AmazonPackages.
 * Individual packages can be found by ASIN.
 */
public class AmazonOrderService {

    private List<AmazonPackage> packages;

    /**
     * Constructs an AmazonOrderService object.
     * @param packages - The List of packages in the order
     */
    public AmazonOrderService(List<AmazonPackage> packages) {
        this.packages = packages;
    }

    /**
     * Does a linear search for a package in the known list of packages.
     * @param asin - The ASIN being searched for.
     * @return the Amazon Package with the target ASIN
     */
    public AmazonPackage findPackageLinear(String asin) throws PackageNotFoundException {
        for (AmazonPackage pack : packages) {
            if (pack.getAsin().equals(asin)) {
                return pack;
            }
        }
        throw new PackageNotFoundException("There is not a package with that ASIN");
    }

    /**
     * Does a binary search for a package in the known list of packages.
     * Note: You should assume that the package list is already sorted when this method is called.
     * @param asin - The ASIN being searched for.
     * @return the Amazon Package with the target ASIN
     */
    public AmazonPackage findPackageBinary(String asin) throws PackageNotFoundException {
        int firstIndex = 0;
        int lastIndex = packages.size() - 1;
        AmazonPackage result = null;

        for (int i = 0; i < packages.size(); i++) {
            //Finding the middle index of list
            int mid = firstIndex + ((lastIndex - firstIndex) / 2);
            //Camparing asin's lexicographically to determine new list to search in
            if (packages.get(mid).getAsin().compareTo(asin) < 0) {
                firstIndex = mid + 1;
                result = packages.get(mid);
            } else if (packages.get(mid).getAsin().compareTo(asin) > 0) {
                lastIndex = mid -1;
                result = packages.get(mid);
            } else if (packages.get(mid).getAsin().compareTo(asin) == 0) {
                result = packages.get(mid);
                break;
            }
        }

        if (packages.isEmpty() || !result.getAsin().equals(asin)) {
            throw new PackageNotFoundException("The package you're looking for could not be found.");
        } else {
            return result;
        }
    }
}
