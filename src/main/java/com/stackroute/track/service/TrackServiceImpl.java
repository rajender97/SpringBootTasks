package com.stackroute.track.service;

import com.stackroute.muzix.service.TrackService;
import com.stackroute.track.Repository.TrackRepository;
import com.stackroute.track.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) {
        Track track1 = trackRepository.save(track);
        return track1;
    }

    @Override
    public List<Track> getTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateComments(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public boolean deleteTrack(int trackId) {
        trackRepository.deleteById(trackId);
        return true;
    }

    @Override
    public Optional<Track> getTrackById(int id) {
        return trackRepository.findById(id);
    }
}

