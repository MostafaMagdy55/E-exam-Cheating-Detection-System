package com.Spring.ExamCheatingDetection.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private  String Title;

    @Column(name="content")
    private String Content;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "message",cascade =
                     {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE})

    List<Replay> replays ;



    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


    public void addReplay(Replay replay) {

        if (replays == null) {
            replays = new ArrayList<>();
        }

        replays.add(replay);
        replay.setMessage(this);
    }


}



