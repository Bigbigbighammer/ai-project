package com.example.service;

import com.example.mapper.SongMapper;
import com.example.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongService {

    @Autowired // 自动注入 SongMapper
    private SongMapper songMapper;

    // 获取所有歌曲
    public List<Song> getAllSongs() {
        return songMapper.findAll();
    }

    // 根据 ID 获取歌曲
    public Song getSongById(Long id) {
        return songMapper.findById(id);
    }

    // 添加歌曲
    @Transactional // 添加事务管理
    public Song addSong(Song song) {
        songMapper.insert(song);
        // 因为 insert 方法设置了 useGeneratedKeys=true 和 keyProperty="id"
        // 所以插入后 song 对象会包含数据库生成的 ID
        return song;
    }

    // 更新歌曲
    @Transactional // 添加事务管理
    public Song updateSong(Long id, Song songDetails) {
        Song existingSong = songMapper.findById(id);
        if (existingSong != null) {
            // 更新字段
            existingSong.setTitle(songDetails.getTitle());
            existingSong.setArtist(songDetails.getArtist());
            existingSong.setAlbum(songDetails.getAlbum());
            existingSong.setGenre(songDetails.getGenre());
            songMapper.update(existingSong);
            return existingSong;
        }
        return null; // 或者抛出异常，表示歌曲未找到
    }

    // 删除歌曲
    @Transactional // 添加事务管理
    public boolean deleteSong(Long id) {
        int rowsAffected = songMapper.deleteById(id);
        return rowsAffected > 0; // 如果影响行数大于0，则删除成功
    }
}
