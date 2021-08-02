package com.example.matine.genre;

import com.example.matine.actor.Actor;
import com.example.matine.actor.ActorRepository;
import com.example.matine.content.Content;
import com.example.matine.content.ContentRepository;
import com.example.matine.content.ContentType;
import com.example.matine.model.Archive;
import com.example.matine.repository.ArchiveRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class GenreConfig {

    @Bean
    CommandLineRunner commandLineRunnerGenre(GenreRepository genreRepository, ContentRepository contentRepository, ArchiveRepository archiveRepository, ActorRepository actorRepository) {
        return args -> {
            Genre fantastik = new Genre(1L,"Fantastik");
            Genre drama = new Genre(2L,"Drama");
            Genre korku = new Genre(3L,"Korku");
            Genre aksiyon = new Genre(4L,"Aksiyon");
            Genre animasyon = new Genre(5L,"Animasyon");

            Content fantastik0 = new Content("Yüzüklerin Efendisi: Yüzük Kardeşliği - 2001","Asırlardır kayıp olan yüzük bulunur ve kaderin garip bir cilvesi sonucu, küçük bir Hobbit olan Frodo’nun eline geçer.Frodo’nun yapması gereken, Kıyamet’in Çatlakları olarak anılan diyara dalarak bu yüzüğü ebediyen ortadan kaldırmaktır.","Fantastik",ContentType.FİLM,1L);
            Content fantastik1 = new Content("Yüzüklerin Efendisi: İki Kule - 2002","Yüzük Kardeşliği üyelerinin her birinin, kardeşlik bozulduktan sonra başlarına gelenler anlatılıyor. Kahramanlarımız, gruplar halinde Orta Dünya’nın en tehlikeli yerlerinde maceralar yaşayacak, yeni kavimler, çoktan unutulmuş medeniyetlerle tanışacak.","Fantastik",ContentType.FİLM,1L);
            Content fantastik2 = new Content("Yüzüklerin Efendisi: Kralın Dönüşü - 2003",". Bir yanda güvenilmez Gollum’un rehberlik ettiği yüzük taşıyıcısı Frodo ve yoldaşı Sam, Mordor’a ulaşıp Tek Yüzük’ü yok edebilecekleri tek yere götürmeye çalışıyorlar. ","Fantastik",ContentType.FİLM,1L);
            Content fantastik3 = new Content("Harry Potter 1 : Felsefe Taşı - 2001", "Harry Potter adında öksüz bir çocuk, 11. doğum gününde, ölen ebeveynlerinin güçlü büyücüler olduğunu ve genç Harry için harikalar dünyasının kapılarını açan saygıdeğer ve gizemli Hogwarts Cadılık ve Büyücülük Okulu'na davet edildiğini keşfeder.","Fantastik",ContentType.FİLM,1L);
            Content fantastik4 = new Content("Harry Potter 2 : Sırlar Odası - 2002", "Harry Potter'ın arkadaşlarından uzakta yaşadığı talihsiz yaz sonrası okula dönüyor ve eski bir kehanet gerçek oluyor gibi görünüyor, çünkü gizemli bir varlık Hogwarts'ın koridorlarında dolaşarak kurbanlarını felç etmiş durumda bırakıyor.","Fantastik",ContentType.FİLM,1L);
            Content fantastik5 = new Content("Harry Potter 3 : Azkaban Tutsağı - 2004", "Harry Potter ve sınıf arkadaşları, kaçan bir mahkumu çevreleyen gizemi araştırdıklarında, genç büyücü için ciddi bir tehdit oluşturabileceğinin farkına varırlar.","Fantastik",ContentType.FİLM,1L);
            Content fantastik6 = new Content("Harry Potter 4 : Ateş Kadehi - 2005", "Harry Potter, kendisini rakip büyü okulları arasındaki tehlikeli bir turnuvada yarışırken bulur, ancak tekrar eden kabuslar yüzünden dikkati dağılır.","Fantastik",ContentType.FİLM,1L);
            Content fantastik7 = new Content("Harry Potter 5 : ZümrüdüAnka Yoldaşlığı - 2007", "Harry, Lord Voldemort'un dönüşü hakkındaki uyarılarının göz ardı edildiğini görünce çok şaşırır ve işleri kendi eline alarak karanlık sanatlara karşı kendilerini savunmaları için küçük bir öğrenci grubunu eğitir.","Fantastik",ContentType.FİLM,1L);
            Content fantastik8 = new Content("Harry Potter 6 : Melez Prens - 2009","Harry Potter,\"Melez Prens'in Mülkü\" olarak işaretlenmiş eski bir kitabı keşfeder ve Lord Voldemort'un karanlık geçmişi hakkında daha fazla şey öğrenmeye başlar." ,"Fantastik",ContentType.FİLM,1L);
            Content fantastik9 = new Content("Harry Potter 7 Bölüm 1 : Ölüm Yadigarları - 2010", "Harry, Ron ve Hermione ölümsüzlük sırrını barındıran Hortkulukları yok etmek için zamana ve kötülüğe karşı yarışırken, büyücülük dünyasındaki en güçlü üç nesnenin varlığını ortaya çıkarırlar: Ölüm Yadigarları.","Fantastik",ContentType.FİLM,1L);
            Content fantastik10 = new Content("Harry Potter 7 Bölüm 2 : Ölüm Yadigarları - 2011", "Serinin en görkemli savaşı başlamaktadır, Harry Potter iki tarafın da tüm güçlerini ortaya serecekleri bu savaşta geçmişiyle ilgili pek çok şeyi öğrenecek, en büyük fedakarlığı da yapmak zorunda kalacaktır.","Fantastik",ContentType.FİLM,1L);

            Content drama0 =  new Content("The Shawshank Redemption: Esaretin Bedeli - 1994","Genç ve başarılı olan Andy Dufresne, karısını ve onun sevgilisini öldürmek suçundan ömür boyu hapis cezası alır. Banker olan Andy Shawshank hapishanesine gönderilir.","Drama", ContentType.FİLM,2L);
            Content drama1 = new Content("The Godfather: Baba - 1972","Don Corleone’nin kızı Connie’nin düğününde, ailenin en küçük oğlu ve bir savaş gazisi olan Michael babasıyla barışır. Bir suikast girişimi, Don’u artık işleri yönetemeyecek duruma düşürünce, ailenin başına Michael ve ağabeyi Sonny geçerler.","Drama",ContentType.FİLM,2L);
            Content drama2 = new Content("The Godfather 2: Baba Bölüm 2 - 1974","Genç Corleone, Amerika’ya yeni gelmiştir. 1917 yılında, New York şehri’nin yerel mafyalarından birinin liderini öldürünce saygınlık kazanır.50 yıl sonra, Michael Corleone, Washington’da senato komitesine aile işleriyle ilgili ifade vermektedir.","Drama", ContentType.FİLM,2L);
            Content drama3 = new Content("12 Kızgın Adam - 1957","Bir jüri, meslektaşlarını kanıtları yeniden gözden geçirmeye zorlayarak adaletin düşmesini önlemeye çalışır.","Drama",ContentType.FİLM,2L);
            Content drama4 = new Content("Schindler’s List - 1993","Oskar Schindler adında Alman işadamının 2. Dünya Savaşı zamanında Polonya’da kurduğu fabrikada Yahudi işçileri çalıştırır. Çalıştırması sayesinde binden fazla yahudinin hayatını kurtarır. Gerçek bir hayat hikayesinden uyarlanmıştır.","Drama",ContentType.FİLM,2L);
            Content drama5 = new Content("Pride and Prejudice - 1995","Jane Austen’ın kaleme aldığı klasik romanın bu uyarlamasıdır.","Drama",ContentType.DİZİ,2L);
            Content drama6 = new Content("The Pacific - 2010","The Pacific, 10 bölümden oluşan II. Dünya Savaşı konulu dizi-film. Amerikan Deniz Piyade Kolordusu'nun Pasifik Cephesi'nde Japon İmparatorluk Deniz Kuvvetleri birliklerine karşı verdiği mücadeleyi konu almaktadır.","Drama", ContentType.DİZİ,2L);
            Content drama7 =  new Content("Band of Brothers :  Kardeşler Takımı - 2001","Stephen Ambrose'un 1942 baharında Gürcistan'daki eğitimleriyle başlayan 101. Hava İndirme Tümeninin 506. Alayının 2. Taburu olan Easy Company adlı II.Dünya Savaşı birimini konu alan 10 bölümlük bir mini dizidir.","Drama",ContentType.DİZİ,2L);
            Content drama8 = new Content("The Song of Names - 2019","Martin, Mortimer Simmons isimli başarılı bir müzisyenin oğludur. Dovidl ise ise dokuz yaşında başarılı bir kemancıdır. Dovidl, Warsaw'dan Martin'in yaşadığı eve gelerek ünlü müzisyen Carl Flesch ile çalışacaktır.", "Drama", ContentType.DİZİ, 2L);

            Content korku0 = new Content("The Shining: Cinnet - 1980","Bir oteli kapalı olduğu kış ayları boyunca ailesiyle mesken edinecek olan yazar Jack Torrance oğlunun metafiziksel varlıkları fark etmeye başlamasıyla yalnız olduklarını düşünen Torrance ailesi şiddetli bir ruhsal çatışmanın içine sürükleneceklerdir.","Korku",ContentType.FİLM,3L);
            Content korku1 = new Content("Cube: Küp - 1997","Bir grup insan bir küp içerisinde uyandıklarında neden orada oldukları hakkında hiçbir fikirleri yoktur. Nasıl oraya geldiklerini hatırlamıyorlardır.","Korku",ContentType.FİLM,3L);
            Content korku2 = new Content("A Quiet Place: Sessiz Bir Yer - 2018","İki çocuklu bir aile, izole bir kırsalda sakin bir yaşam sürmektedir.Aile ses yapacak her türlü hareketten uzak durmaktadır. Ancak günün birinde bu sakin hayat, küçük çocukların oyun oynarken bir lambayı devirmeleri ile tepetaklak olur.", "Korku",ContentType.FİLM,3L);
            Content korku3 = new Content("It: O - 2017","Maine'in küçük bir kasabasında yaşayan 7 çocuğu ele alıyor. Dışlanan bu arkadaş grubu bir yandan hayatın getirdiği sorunlarla, bir yandan da ergenlikle uğraşırken, başlarına bir de bir palyaço kılığında dehşet saçan Pennywise bela oluyor.","Korku",ContentType.FİLM,3L);
            Content korku4 = new Content("The Conjuring: Korku Seansı - 2013","Ed ve Lorraine Warren doğaüstü, paranormal olayları araştıran bir çifttir. Bir gün Perron ailesinden bir telefon alırlar. Ailenin çiftlik evi nedeni bulunmayan karanlık bir varlık tarafından kuşatılmıştır; hayatları alt üst olmuştur.","Korku",ContentType.FİLM,3L);

            Content aksiyon0 = new Content("The Lost - 2004-2010","Oceanic Havayolları’nın Sidney-Los Angeles seferini yapan 815 sefer sayılı uçağı, okyanus üzerinden geçerken, manyetik bir alana kapılarak büyük bir adaya düşer. Adanın kazazedelerin her birinin hayatını farklı biçimde değiştireceğinden habersizdirler.","Aksiyon",ContentType.DİZİ,4L);
            Content aksiyon1 = new Content("The Boys - 2019","Süper kahramanların güçlerini ve şöhretlerini kötüye kullandığı bir evrende geçiyor. Kendilerini kanunu korumaya adayan ve “The Boys” olarak adlandırılan bir grup insan, yozlaşmış süper kahramanları ortadan kaldırmak için zorlu bir yolculuğa çıkar.","Aksiyon",ContentType.DİZİ,4L);
            Content aksiyon2 = new Content("Shadowhunters 2016-2019","Shadowhunters, Clary Fray isimli genç bir kızın 18. yaş gününde yarı insan yarı melek olan ve şeytanlarla savaşan Shadowhunter soyundan geldiğini öğrenmesiyle değişen hayatını konu alıyor.","Aksiyon",ContentType.DİZİ,4L);
            Content aksiyon3 = new Content("Django Unchained: Zincirsiz - 2012","Film Amerikan İç Savaşı'ndan iki yıl önce başlayan hikaye geçmişinde eziyet çekmiş bir köle ile Alman avcı Dr. King Schultz'un yüzleşmesini merkezine alıyor.","Aksiyon",ContentType.FİLM,4L);
            Content aksiyon4 = new Content("The Gifted - 2017-2019","Banliyöde yaşayan bir ailenin, çocuklarının mutant güçleri olduğunu bir anda öğrenince değişen hayatlarını anlatıyor.","Aksiyon",ContentType.DİZİ,4L);
            Content aksiyon5 = new Content("The Dark Knight: Kara Şövalye - 2008","Kara Şövalye' de, Yarasa Adam suça karşı savaşını daha da ileriye götürüyor: şehir sokaklarını sarmış olan suç örgütlerinden geriye kalanları temizlemeye girişir.","Aksiyon",ContentType.FİLM,4L);
            Content aksiyon6 = new Content("Kill Bill Vol 1 - 2003","Gelin, zamanında kadın suikastçılardan oluşan bir grubunun parçasıdır. Bill ve öteki suikastçılar ona karşı birleşince konumunu bırakmıştır. Düğün günü kendisine kurulan bir tuzak nedeniyle girdiği komadan uyanınca intikam almaya yemin eder!","Aksiyon",ContentType.FİLM,4L);
            Content aksiyon7 = new Content("Riders of Justice - 2020","Asker olan Markus, karısının trajik bir tren kazasında ölmesiyle büyük bir yıkıma uğrar.Markus kısa bir süre sonra, karısının ölümüne neden olan tren kazasının altında farklı gerçekler olduğunu keşfeder VEkarısının intikamını almak için harekete geçer.","Aksiyon",ContentType.FİLM,4L);

            Content animasyon0 = new Content("Ejderhanı Nasıl Eğitirsin - 2010","Berk Adası yıllardır ejderler tarafından yağmalanmaktadır.Yetişkinlik çağına erişen genç erkeklerin yapması gereken tek şey bir ejderhayı öldürmektir.Köyün şefinin tuhaf oğlu Hiccup ve bir ejder arasında sadakat ve içtenlik barındıran bir dostluk gelişir.","Animasyon",ContentType.FİLM,5L);
            Content animasyon1 = new Content("Ejderhanı Nasıl Eğitirsin 2 - 2014","Berk Adası'nda ejderha ve vikinglerin birleşmesinin üstünden beş yıl geçmiştir ve adada tam bir barış ve huzur atmosferi hakimdir. Kahramanlarımız çok eski zamanlardan kalma buzdan gizli bir mağara keşfederler. ","Animasyon",ContentType.FİLM,5L);
            Content animasyon2 = new Content("Soul - 2020","Soul, caz sanatçısı olmayı hayal eden bir ortaokul müzik öğretmenine odaklanıyor.Karakterimiz ruhunun bedeninden ayrılmasına neden olan bir kaza geçirir ve başka bir boyuta geçer.22 ile tanışır ve şekilde Joe'nın geri dönmesine yardım edecektir.","Animasyon",ContentType.FİLM,5L);
            Content animasyon3 = new Content("Mary ve Max - 2009","Mary, Avustralya'nın kenar mahallerinden birinde yaşayan, sorumsuz ve yoksul bir aileye sahip olan, sekiz yaşındaki yalnız bir kız çocuğudur. Küçük kızın konuşabildiği tek kişi mektuplaştığı Avustralyalı bir savaş gazisidir.","Animasyon",ContentType.FİLM,5L);
            Content animasyon4 = new Content("Corpse Bride: Ölü Gelin - 2005","Victor kısa süre sonra güzel Victoria ile evlenecektir. Kendi kendine yüzük takma provası yaparken yüzüğü yanlışlıkla Ölü Gelin'in parmağına takıverir ve apar topar Ölüler Diyarı'na götürülür. Ölüler Diyarı sıkıcılıktaqn uzak ve çok eğlenceli bir yerdir.","Animasyon",ContentType.FİLM,5L);
            Content animasyon5 = new Content("Inside Out: Ters Yüz - 2015","Babası San Francisco’da yeni bir işe başlayınca Orta-Batı’daki hayatından kopmak zorunda kalan Riley de hepimiz gibi duyguları ile hareket eden bir kızdır.Bu duygular, Riley’nin zihninin içinde ana merkezde yaşar, ona günlük hayatında tavsiyeler verirler.","Animasyon",ContentType.FİLM,5L);
            Content animasyon6 = new Content("WALL·E - 2008","İnsanoğlu aşırı kirlenme sebebiyle Dünya’yı terk etmiştir.Çöplerle çevrili dünyayı temizleme görevi, Vol.i’ye verilir.İnsanoğlunun bıraktığı çöplerden kendine yeni bir dünya yaratan Vol.i’nin yalnızlığı,Eve’nın gelmesiyle son bulur.", "Animasyon",ContentType.FİLM,5L);
            Content animasyon7 = new Content("Onward: Hadi Gidelim - 2020","İki kardeş, hatırlamakta zorlandıkları ve çok özledikleri babaları ile son bir gün geçirme fırsatlarının olduğunu öğrenirler.Kendilerini zorlu bir mücadeleye sokan kardeşler, büyüye ulaşıp babalarını son bir kez görebilme imkanını bulabilecekler midir?","Animasyon",ContentType.FİLM,5L);
            Content animasyon8 = new Content("Toy Story: Oyuncak Hikayesi - 1995","Andy oyuncak koleksiyonu yapan ve oyuncaklarına değer veren bir çocuktur.Andy’nin yokluğunda,oyuncakları kovboy Woody’nin önderliğinde hayata gelir. Bir gün Andy bir hediye alır ve hediye oyuncaklar arasındaki ego tartışmalarını beraberinde getirecektir.","Animasyon",ContentType.FİLM,5L);
            Content animasyon9 = new Content("Tangled: Karmakarışık - 2010","Sihirli uzun saçları olan Rapunzel bütün hayatını bir kulede geçirmiştir. Ama kanundan kaçıp bu kuleye sığınan bir hırsız Rapunzel'in hayatını baştan aşağı değiştirecektir. Rapunzel ilk defa dünyayı keşfetmek ve gerçekte kim olduğunu öğrenmek üzeredir.","Animasyon",ContentType.FİLM,5L);
            Content animasyon10 = new Content("Ratatouille: Ratatuy - 2007","Şişman fare Remy, yemeğe olan düşkünlüğü nedeniyle tek bir hayale sahiptir: Aşçı olabilmek!Ancak bu tek hayalini gerçekleştirmek, farelerden iğrenen bir insanlığın var olduğu bir dünyada pek kolay olmayacaktır.","Animasyon",ContentType.FİLM,5L);

            Archive  archive1= new Archive(
                    1L,
                    1L
            );
            Archive  archive2= new Archive(
                    1L,
                    2L
            );
            Archive  archive3= new Archive(
                    1L,
                    3L
            );
            Archive  archive4= new Archive(
                    1L,
                    12L
            );

            Archive  archive5= new Archive(
                    1L,
                    21L
            );
            Archive  archive6= new Archive(
                    1L,
                    34L
            );
            Archive  archive7= new Archive(
                    1L,
                    35L
            );
            Archive  archive8= new Archive(
                    1L,
                    37L
            );
            Archive  archive9= new Archive(
                    1L,
                    38L
            );
            Archive  archive10= new Archive(
                    1L,
                    39L
            );
            Archive  archive11= new Archive(
                    1L,
                    42L
            );
            Archive  archive12= new Archive(
                    3L,
                    14L
            );
            Archive  archive13= new Archive(
                    3L,
                    12L
            );
            Archive  archive14= new Archive(
                    3L,
                    13L
            );
            Archive  archiv15= new Archive(
                    2L,
                    1L
            );
            Archive  archive16= new Archive(
                    2L,
                    2L
            );
            Archive  archive17= new Archive(
                    2L,
                    3L
            );
            Archive  archive18= new Archive(
                    7L,
                    25L
            );
            Archive  archive19= new Archive(
                    7L,
                    32L
            );
            Archive  archive20= new Archive(
                    8L,
                    12L
            );
            Archive  archive21= new Archive(
                    8L,
                    32L
            );

            Actor actor0= new Actor(
                    1L,
                    "Elijah Wood",
                    "Frodo Baggins"
            );
            Actor actor1= new Actor(
                    1L,
                    "Sean Astin",
                    "Samwise 'Sam' Gamgee"
            );
            Actor actor2= new Actor(
                    1L,
                    "Ian McKellen",
                    "Gandalf"
            );
            Actor actor3= new Actor(
                    1L,
                    "Sala Baker",
                    "Sauron"
            );
            Actor actor4= new Actor(
                    1L,
                    "Viggo Mortensen",
                    "Aragorn"
            );
            Actor actor5= new Actor(
                    1L,
                    "Christopher Lee",
                    "Saruman"
            );
            Actor actor6= new Actor(
                    1L,
                    "Ian Holm",
                    "Bilbo Baggins"
            );
            Actor actor7= new Actor(
                    1L,
                    "John Rhys-Davies",
                    "Gimli"
            );
            Actor actor8= new Actor(
                    1L,
                    "Dominic Monaghan",
                    "Meriadoc 'Merry' Brandybuck"
            );
            Actor actor9= new Actor(
                    1L,
                    "Billy Boyd",
                    "Peregrin 'Pippin' Took"
            );
            Actor actor10= new Actor(
                    1L,
                    "Orlando Bloom",
                    "Legolas"
            );
            Actor actor11= new Actor(
                    1L,
                    "Hugo Weaving",
                    "Elrond"
            );
            Actor actor12= new Actor(
                    1L,
                    "Cate Blanchett",
                    "Gladriel"
            );
            Actor actor13= new Actor(
                    1L,
                    "Liv Tyler",
                    "Arwen"
            );
            Actor actor14= new Actor(
                    1L,
                    "Lawrence Makoare",
                    "Lurtz"
            );
            Actor actor15= new Actor(
                    1L,
                    "Marton Csokas",
                    "Lord Celeborn"
            );
            Actor actor16= new Actor(
                    1L,
                    "Andy Serkis",
                    "Sméagol - Gollum"
            );
            Actor actor17= new Actor(
                    1L,
                    "Craig Parker",
                    "Haldir"
            );
            Actor actor18= new Actor(
                    2L,
                    "Elijah Wood",
                    "Frodo Baggins"
            );
            Actor actor19= new Actor(
                    2L,
                    "Sean Astin",
                    "Samwise 'Sam' Gamgee"
            );
            Actor actor20= new Actor(
                    2L,
                    "Ian McKellen",
                    "Gandalf"
            );
            Actor actor21= new Actor(
                    2L,
                    "Viggo Mortensen",
                    "Aragorn"
            );
            Actor actor22= new Actor(
                    2L,
                    "Christopher Lee",
                    "Saruman"
            );

            Actor actor23= new Actor(
                    2L,
                    "John Rhys-Davies",
                    "Gimli"
            );
            Actor actor24= new Actor(
                    2L,
                    "Dominic Monaghan",
                    "Meriadoc 'Merry' Brandybuck"
            );
            Actor actor25= new Actor(
                    2L,
                    "Billy Boyd",
                    "Peregrin 'Pippin' Took"
            );
            Actor actor26= new Actor(
                    2L,
                    "Orlando Bloom",
                    "Legolas"
            );
            Actor actor27= new Actor(
                    2L,
                    "Hugo Weaving",
                    "Elrond"
            );
            Actor actor28= new Actor(
                    2L,
                    "Cate Blanchett",
                    "Gladriel"
            );
            Actor actor29= new Actor(
                    2L,
                    "Liv Tyler",
                    "Arwen"
            );

            Actor actor30= new Actor(
                    2L,
                    "Andy Serkis",
                    "Sméagol - Gollum"
            );
            Actor actor31= new Actor(
                    1L,
                    "Craig Parker",
                    "Haldir"
            );
            Actor actor32= new Actor(
                    3L,
                    "Elijah Wood",
                    "Frodo Baggins"
            );
            Actor actor33= new Actor(
                    3L,
                    "Sean Astin",
                    "Samwise 'Sam' Gamgee"
            );
            Actor actor34= new Actor(
                    3L,
                    "Ian McKellen",
                    "Gandalf"
            );
            Actor actor35= new Actor(
                    3L,
                    "Viggo Mortensen",
                    "Aragorn"
            );
            Actor actor36= new Actor(
                    3L,
                    "Christopher Lee",
                    "Saruman"
            );

            Actor actor37= new Actor(
                    3L,
                    "John Rhys-Davies",
                    "Gimli"
            );
            Actor actor38= new Actor(
                    3L,
                    "Dominic Monaghan",
                    "Meriadoc 'Merry' Brandybuck"
            );
            Actor actor39= new Actor(
                    3L,
                    "Billy Boyd",
                    "Peregrin 'Pippin' Took"
            );
            Actor actor40= new Actor(
                    3L,
                    "Orlando Bloom",
                    "Legolas"
            );
            Actor actor41= new Actor(
                    3L,
                    "Hugo Weaving",
                    "Elrond"
            );
            Actor actor42= new Actor(
                    3L,
                    "Cate Blanchett",
                    "Gladriel"
            );
            Actor actor43= new Actor(
                    3L,
                    "Liv Tyler",
                    "Arwen"
            );

            Actor actor44= new Actor(
                    3L,
                    "Andy Serkis",
                    "Sméagol - Gollum"
            );
            Actor actor45= new Actor(
                    3L,
                    "Craig Parker",
                    "Haldir"
            );

            Actor actor46= new Actor(
                    4L,
                    "Daniel Radcliffe",
                    "Harry Potter"
            );
            Actor actor47= new Actor(
                    4L,
                    "Emma Watson",
                    "Hermione Granger"
            );
            Actor actor48= new Actor(
                    4L,
                    "Rupert Grint",
                    "Ron Weasley"
            );
            Actor actor49= new Actor(
                    4L,
                    "Robbie Coltrane",
                    "Rubeus Hagrid"
            );
            Actor actor50= new Actor(
                    4L,
                    "Richard Harris",
                    "Albus Dumbledore"
            );
            Actor actor51= new Actor(
                    4L,
                    "Maggie Smith",
                    "Minerva McGonagall"
            );
            Actor actor52= new Actor(
                    4L,
                    "Alan Rickman",
                    "Severus Snape"
            );
            Actor actor53= new Actor(
                    4L,
                    "Tom Felton",
                    "Draco Malfoy"
            );
            Actor actor54= new Actor(
                    5L,
                    "Daniel Radcliffe",
                    "Harry Potter"
            );
            Actor actor55= new Actor(
                    5L,
                    "Emma Watson",
                    "Hermione Granger"
            );
            Actor actor56= new Actor(
                    5L,
                    "Rupert Grint",
                    "Ron Weasley"
            );
            Actor actor57= new Actor(
                    5L,
                    "Robbie Coltrane",
                    "Rubeus Hagrid"
            );
            Actor actor58= new Actor(
                    5L,
                    "Richard Harris",
                    "Albus Dumbledore"
            );
            Actor actor59= new Actor(
                    5L,
                    "Maggie Smith",
                    "Minerva McGonagall"
            );
            Actor actor60= new Actor(
                    5L,
                    "Alan Rickman",
                    "Severus Snape"
            );
            Actor actor61= new Actor(
                    5L,
                    "Tom Felton",
                    "Draco Malfoy"
            );
            archiveRepository.saveAll(List.of(archive1,archive2,archive3,archive4,archive5,archive6,archive7,archive8,archive9,archive10,archive11,archive12,
                    archive13,archive14,archiv15,archive16,archive17,archive18,archive19,archive20,archive21));
            contentRepository.saveAll(List.of(fantastik0,fantastik1,fantastik2,fantastik3,fantastik4,fantastik5,fantastik6,fantastik7,fantastik8,fantastik9,fantastik10));
            contentRepository.saveAll(List.of(korku0,korku1,korku2,korku3,korku4));
            contentRepository.saveAll(List.of(aksiyon0,aksiyon1,aksiyon2,aksiyon3,aksiyon4,aksiyon5,aksiyon6,aksiyon7));
            contentRepository.saveAll(List.of(drama0,drama1,drama2,drama3,drama4,drama5,drama6,drama7,drama8));
            contentRepository.saveAll(List.of(animasyon0,animasyon1,animasyon2,animasyon3,animasyon4,animasyon5,animasyon6,animasyon7,animasyon8,animasyon9,animasyon10));
            genreRepository.saveAll(List.of(fantastik,drama,korku,aksiyon,animasyon));
            actorRepository.saveAll(List.of(actor0,actor1,actor2,actor3,actor4,actor5,actor6,actor7,actor8,actor9,actor10,
                    actor11,actor12,actor13,actor14,actor15,actor16,actor17,actor18,actor19,actor20,actor21,actor22,actor23,actor24,
                    actor25,actor26,actor27,actor28,actor29,actor30,actor31,actor32,actor33,actor34,actor35,actor36,actor37,actor38,
                    actor39,actor40,actor41,actor42,actor43,actor44,actor45,actor46,actor47,actor48,actor49,actor50,actor51,actor52,
                    actor53,actor54,actor55,actor56,actor57,actor58,actor59,actor60,actor61));


        };
    }
}
