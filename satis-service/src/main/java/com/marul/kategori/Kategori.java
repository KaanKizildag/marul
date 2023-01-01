package com.marul.kategori;

import com.marul.urun.Urun;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "kategori")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kategori_adi")
    private String kategoriAdi;

    @OneToMany(mappedBy = "kategori")
    private List<Urun> urun;
}
