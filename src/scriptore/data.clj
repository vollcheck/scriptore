(ns scriptore.data)

;; Forgive me weird formatting although that
;; facilitates text operations for good...

(def shorters {
"Rdz" :historical
"Wj" :historical
"Kpł" :historical
"Lb" :historical
"Pwt" :historical
"Joz" :historical
"Sdz" :historical
"Rt" :historical
"1 Sm" :historical
"2 Sm" :historical
"1 Krl" :historical
"2 Krl" :historical
"1 Krn" :historical
"2 Krn" :historical
"Ezd" :historical
"Ne" :historical
"Tb" :historical
"Jdt" :historical
"Est" :historical
"1 Mch" :historical
"2 Mch" :historical
"Hi" :historical
"Ps" :historical
"Prz" :historical
"Koh" :historical
"Pnp" :historical
"Mdr" :historical
"Syr" :historical
"Iz" :prophetic
"Jr" :prophetic
"Lm" :prophetic
"Ba" :prophetic
"Ez" :prophetic
"Dn" :prophetic
"Oz" :prophetic
"Jl" :prophetic
"Am" :prophetic
"Ab" :prophetic
"Jon" :prophetic
"Mi" :prophetic
"Na" :prophetic
"Ha" :prophetic
"So" :prophetic
"Ag" :prophetic
"Za" :prophetic
"Ml" :prophetic
"Mt" :evangelic
"Mk" :evangelic
"Łk" :evangelic
"J" :evangelic
"Dz" :non-evangelic
"Rz" :non-evangelic
"1 Kor" :non-evangelic
"2 Kor" :non-evangelic
"Ga" :non-evangelic
"Ef" :non-evangelic
"Flp" :non-evangelic
"Kol" :non-evangelic
"1 Tes" :non-evangelic
"2 Tes" :non-evangelic
"1 Tm" :non-evangelic
"2 Tm" :non-evangelic
"Tt" :non-evangelic
"Flm" :non-evangelic
"Hbr" :non-evangelic
"Jk" :non-evangelic
"1 P" :non-evangelic
"2 P" :non-evangelic
"1 J" :non-evangelic
"2 J" :non-evangelic
"3 J" :non-evangelic
"Jud" :non-evangelic
"Ap" :non-evangelic
})

(def longers {
:historical [
"Ks. Rodzaju"
"Ks. Wyjścia"
"Ks. Kapłańska"
"Ks. Liczb"
"Ks. Powtórzonego Prawa"
"Ks. Jozuego"
"Ks. Sędziów"
"Ks. Rut"
"1 Ks. Samuela"
"2 Ks. Samuela"
"1 Ks. Królewska"
"2 Ks. Królewska"
"1 Ks. Kronik"
"2 Ks. Kronik"
"Ks. Ezdrasza"
"Ks. Nehemiasza"
"Ks. Tobiasza"
"Ks. Judyty"
"Ks. Estery"
"1 Ks. Machabejska"
"2 Ks. Machabejska"
"Ks. Hioba"
"Ks. Psalmów"
"Ks. Przysłów"
"Ks. Koheleta"
"Pieśń nad Pieśniami"
"Ks. Mądrości"
"Mądrość Syracha"
]

:prophetic [
"Ks. Izajasza"
"Ks. Jeremiasza"
"Lamentacje"
"Ks. Barucha"
"Ks. Ezechiela"
"Ks. Daniela"
"Ks. Ozeasza"
"Ks. Joela"
"Ks. Amosa"
"Ks. Abdiasza"
"Ks. Jonasza"
"Ks. Micheasza"
"Ks. Nahuma"
"Ks. Habakuka"
"Ks. Sofoniasza"
"Ks. Aggeusza"
"Ks. Zachariasza"
"Ks. Malachiasza"
]

:evangelic [
"Ew. wg św. Mateusza"
"Ew. wg św. Marka"
"Ew. wg św. Łukasza"
"Ew. wg św. Jana"
]

:non-evangelic [
"Dzieje Apostolskie"
"List do Rzymian"
"1 List do Koryntian"
"2 List do Koryntian"
"List do Galatów"
"List do Efezjan"
"List do Filipian"
"List do Kolosan"
"1 List do Tesaloniczan"
"2 List do Tesaloniczan"
"1 List do Tymoteusza"
"2 List do Tymoteusza"
"List do Tytusa"
"List do Filemona"
"List do Hebrajczyków"
"List św. Jakuba"
"1 List św. Piotra"
"2 List św. Piotra"
"1 List św. Jana"
"2 List św. Jana"
"3 List św. Jana"
"List św. Judy"
"Apokalipsa św. Jana"
]})
