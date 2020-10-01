package com.nttdata.justinpoc.agency;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgencyDelagateImpl implements com.nttdata.justinpoc.api.AgencyApiDelegate {

    @Override
    public ResponseEntity<List<com.nttdata.justinpoc.api.model.Agency>> listAgency() {

        List<com.nttdata.justinpoc.api.model.Agency> result = new ArrayList<>();

        com.nttdata.justinpoc.api.model.Agency agency1 = new com.nttdata.justinpoc.api.model.Agency();
        agency1.setId(UUID.randomUUID());
        agency1.setName("agency1");
        result.add(agency1);

        com.nttdata.justinpoc.api.model.Agency agency2 = new com.nttdata.justinpoc.api.model.Agency();
        agency2.setId(UUID.randomUUID());
        agency2.setName("agency2");
        result.add(agency2);

        return ResponseEntity.ok(result);


    }
}
