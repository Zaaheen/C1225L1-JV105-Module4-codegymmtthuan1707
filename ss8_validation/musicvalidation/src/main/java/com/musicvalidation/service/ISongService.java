package com.musicvalidation.service;

import com.musicvalidation.entity.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    List<Song> findAll();
    Optional<Song> findById(Long id);
    Song save(Song song);
}
