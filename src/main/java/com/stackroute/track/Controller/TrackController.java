package com.stackroute.track.Controller;


import com.stackroute.muzix.service.TrackService;
import com.stackroute.track.domain.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value="api/v1")
public class TrackController<TrackDao> {
    TrackService trackService;




    public TrackController(TrackService trackService){

        this.trackService=trackService;
    }


    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
           trackService.saveTrack(track);
           responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }




    @GetMapping("track")
    public ResponseEntity<?> getTracks(){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }





    @PutMapping("track/{id}")
    public  ResponseEntity<?> updateTracks(@PathVariable(value = "id") int id,@Valid @RequestBody Track track){
        ResponseEntity responseEntity;
        Optional<Track> track1 = trackService.getTrackById(id);
        try{
            if(!track1.isPresent()){
                throw new Exception("id-"+id);
            }
            track.setTrackId(id);
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }





    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTracks(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try{
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }
}