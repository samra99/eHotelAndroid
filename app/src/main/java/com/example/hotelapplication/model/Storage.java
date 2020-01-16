package com.example.hotelapplication.model;
import com.example.hotelapplication.R;
import com.example.hotelapplication.helper.MyObjects;

import java.util.ArrayList;
import java.util.List;

public class Storage {
  private static List<Soba> sobe;

  public static List<Soba> getSobe(){

      int []imgs=getImages();
      int []imgsUsluge=getImagesUsluge();
      if(sobe==null){
          sobe=new ArrayList<>();
          sobe.add(new Soba(imgs[1],imgsUsluge[0],"Bracna soba","TV, Wi-Fi, Minibar","Bez obzira da li odaberete očaravajući pogled na grad ili sobu sa pogledom na predivni Zemaljski muzej – uživaćete u savremenom luksuzu i tradicionalnom ugođaju.Sobe modernog dizajna zadovoljiti će potrebe svakog gosta.Sadržaj u sobama: bepslatan pristup WiFi internet, luksuzni mini bar sa grickalicama i pićima, TV sa interaktivnim okruženjem, bade mantil i papuče, fen za kosu, sef u sobi, room service 24/7, krevetac za bebu na upit."));
          sobe.add(new Soba(imgs[2],imgsUsluge[1],"Dvokrevetna","TV, Wi-Fi, Minibar","Bez obzira da li odaberete očaravajući pogled na grad ili sobu sa pogledom na predivni Zemaljski muzej – uživaćete u savremenom luksuzu i tradicionalnom ugođaju.Sobe modernog dizajna zadovoljiti će potrebe svakog gosta.Sadržaj u sobama: bepslatan pristup WiFi internet, luksuzni mini bar sa grickalicama i pićima, TV sa interaktivnim okruženjem, bade mantil i papuče, fen za kosu, sef u sobi, room service 24/7, krevetac za bebu na upit."));
          sobe.add(new Soba(imgs[3],imgsUsluge[2],"Trokrevetna","TV, Wi-Fi, Minibar","Bez obzira da li odaberete očaravajući pogled na grad ili sobu sa pogledom na predivni Zemaljski muzej – uživaćete u savremenom luksuzu i tradicionalnom ugođaju.Sobe modernog dizajna zadovoljiti će potrebe svakog gosta.Sadržaj u sobama: bepslatan pristup WiFi internet, luksuzni mini bar sa grickalicama i pićima, TV sa interaktivnim okruženjem, bade mantil i papuče, fen za kosu, sef u sobi, room service 24/7, krevetac za bebu na upit."));
          sobe.add(new Soba(imgs[4],imgsUsluge[3],"Cetverokrevetna","TV, Wi-Fi, Minibar","Bez obzira da li odaberete očaravajući pogled na grad ili sobu sa pogledom na predivni Zemaljski muzej – uživaćete u savremenom luksuzu i tradicionalnom ugođaju.Sobe modernog dizajna zadovoljiti će potrebe svakog gosta.Sadržaj u sobama: bepslatan pristup WiFi internet, luksuzni mini bar sa grickalicama i pićima, TV sa interaktivnim okruženjem, bade mantil i papuče, fen za kosu, sef u sobi, room service 24/7, krevetac za bebu na upit."));
          sobe.add(new Soba(imgs[5],imgsUsluge[0],"Apartman","TV, Wi-Fi, Minibar","Bez obzira da li odaberete očaravajući pogled na grad ili sobu sa pogledom na predivni Zemaljski muzej – uživaćete u savremenom luksuzu i tradicionalnom ugođaju.Sobe modernog dizajna zadovoljiti će potrebe svakog gosta.Sadržaj u sobama: bepslatan pristup WiFi internet, luksuzni mini bar sa grickalicama i pićima, TV sa interaktivnim okruženjem, bade mantil i papuče, fen za kosu, sef u sobi, room service 24/7, krevetac za bebu na upit."));
          sobe.add(new Soba(imgs[6],imgsUsluge[0],"Apartman","TV, Wi-Fi, Minibar","Bez obzira da li odaberete očaravajući pogled na grad ili sobu sa pogledom na predivni Zemaljski muzej – uživaćete u savremenom luksuzu i tradicionalnom ugođaju.Sobe modernog dizajna zadovoljiti će potrebe svakog gosta.Sadržaj u sobama: bepslatan pristup WiFi internet, luksuzni mini bar sa grickalicama i pićima, TV sa interaktivnim okruženjem, bade mantil i papuče, fen za kosu, sef u sobi, room service 24/7, krevetac za bebu na upit."));
      }


      return sobe;
  }

    public static int[] getImages() {


        int[] images = {R.drawable.jednokrevetna,R.drawable.bracna,R.drawable.dvokrevetna,R.drawable.trokrevetna
                ,R.drawable.cetverokrevetna,R.drawable.delux,R.drawable.apartman
        };
        return images;
    }
    public static int[] getImagesUsluge() {


        int[] images = {R.drawable.jednokrevetna,R.drawable.dvokrevetna,R.drawable.trokrevetna,R.drawable.cetverokrevetna};


        return images;
    }


    private  static List<Korisnik> korisnici;
  public  static List<Korisnik> getKorisnici(){
      if (korisnici==null){
          korisnici=new ArrayList<>();
          korisnici.add(new Korisnik(1,"Amina","Cerkez","Amina","test","amina.cerkez@edu.fit.ba"));
          korisnici.add(new Korisnik(2,"Enida","Obradovic","Enida","enida","enida.obradovic@edu.fit.ba"));
          korisnici.add(new Korisnik(3,"Harisa","Obad","Harisa","harisa1","harisa.obad@edu.fit.ba"));
          korisnici.add(new Korisnik(4,"Test","test","Test","testt","testtest@edu.fit.ba"));
          korisnici.add(new Korisnik(5,"Dino","test","Dino","dinotest","dino.fisic@edu.fit.ba"));

      }

      return korisnici;
  }

   private static List<Rezervacija> rezervacije;
   public static List<Rezervacija> getRezervacije() {
      if (rezervacije==null){
         rezervacije=new ArrayList<>();
          rezervacije.add(new Rezervacija(getKorisnici().get(0),1,"13/09/2019","12/09/2019",1,0));



      }

      return rezervacije;
   }
public static void addRezervacija(Rezervacija rez)
{
    getRezervacije().add(rez);

}
    public static void RemoveRezervacija(Rezervacija rez)
    {
        getRezervacije().remove(rez);

    }
    public static List<Korisnik> getKorisniciByIme(String query) {

        List<Korisnik> rezultat = new ArrayList<>();

        for (Korisnik x: getKorisnici())
        {
            if ((x.getIme() + " " + x.getPrezime()).startsWith(query))
                rezultat.add(x);
        }

        return rezultat;
    }
    public static Korisnik LoginCheck(String username, String password)
    {
        for (Korisnik x : getKorisnici()) {
            if (MyObjects.equals(x.getUsername(), username) && MyObjects.equals(x.getPassword(), password))
                return x;
        }
        return null;
    }




}
