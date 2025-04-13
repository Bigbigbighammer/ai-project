package com.example.controller;

import com.example.model.Song;
import com.example.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 标记为 REST 控制器
@RequestMapping("songs") // 所有请求路径都以 /api/songs 开头
// @CrossOrigin // 或者在这里配置更细粒度的跨域，但 application.properties 中已全局配置
public class SongController {

    @Autowired // 自动注入 SongService
    private SongService songService;

    // GET /api/songs - 获取所有歌曲
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs); // 返回 200 OK 和歌曲列表
    }

    // GET /api/songs/{id} - 根据 ID 获取歌曲
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        if (song != null) {
            return ResponseEntity.ok(song); // 返回 200 OK 和歌曲信息
        } else {
            return ResponseEntity.notFound().build(); // 返回 404 Not Found
        }
    }

    // POST /api/songs - 添加新歌曲
    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        // @RequestBody 将请求体中的 JSON 转换为 Song 对象
        try {
            Song createdSong = songService.addSong(song);
            // 返回 201 Created 和新创建的歌曲信息
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
        } catch (Exception e) {
            // 简单的错误处理
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT /api/songs/{id} - 更新歌曲信息
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song songDetails) {
        try {
            Song updatedSong = songService.updateSong(id, songDetails);
            if (updatedSong != null) {
                return ResponseEntity.ok(updatedSong); // 返回 200 OK 和更新后的歌曲信息
            } else {
                return ResponseEntity.notFound().build(); // 返回 404 Not Found
            }
        } catch (Exception e) {
            // 简单的错误处理
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /api/songs/{id} - 删除歌曲
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        try {
            boolean deleted = songService.deleteSong(id);
            if (deleted) {
                return ResponseEntity.noContent().build(); // 返回 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 返回 404 Not Found
            }
        } catch (Exception e) {
            // 简单的错误处理
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
