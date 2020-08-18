<?php
    require 'connDB.php';

    //Sql nya benerin lagi, perhatiin apa aja yang mau ditampilin
    $sql = "SELECT * FROM masjid INNER JOIN kordinatmasjid on masjid.id_masjid = kordinatmasjid.id_masjid";
    $query = mysqli_query($conn, $sql);

    while($r = mysqli_fetch_array($query)) {
        $r['nama_masjid'];
        $r['alamat'];
    }
?>