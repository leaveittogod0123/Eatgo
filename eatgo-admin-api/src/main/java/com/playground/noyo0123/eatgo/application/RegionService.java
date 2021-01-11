package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.Region;
import com.playground.noyo0123.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();
//        List<Region> regions = new ArrayList<>();
//        regions.add(Region.builder().name("Seoul").build());
        return regions;
    }

    public Region addRegion(String seoul) {
        Region region = Region.builder().name("Seoul").build();
        regionRepository.save(region);
        return region;
    }
}
