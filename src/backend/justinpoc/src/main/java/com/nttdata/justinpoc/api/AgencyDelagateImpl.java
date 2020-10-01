package com.nttdata.justinpoc.api;

import com.nttdata.justinpoc.api.model.Agency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgencyDelagateImpl implements com.nttdata.justinpoc.api.AgencyApiDelegate {

    @Override
    public ResponseEntity<List<Agency>> listAgency() {

        List<Agency> result = new ArrayList<>();


        Agency agency1 = new Agency();
        agency1.setId(UUID.randomUUID());
        agency1.setName("agency1");
        result.add(agency1);

        Agency agency2 = new Agency();
        agency2.setId(UUID.randomUUID());
        agency2.setName("agency2");
        result.add(agency2);

        return ResponseEntity.ok(result);


    }
}
