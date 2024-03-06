package com.larffxx.remoteaccessbot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TextData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long pcId;

    private String userName, text;
    public TextData(String userName, String text ) {
        this.userName = userName;
        this.text = text;
    }
    @Override
    public String toString() {
        return userName + " " + text;
    }

}
