import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*
Senaryo 1
• Uygulama açılır
• Anasayfada Konum alanına tıklanır.
• İl, ilçe ve mahalle seçilir ve kaydet butonuna Kklanır.
• Konumunuz Kaydedildi popup’ı kontrol edilir.
• Tab bar üzerinden kategoriler tabına tıklanır.
• Herhangi bir kategori seçilip listeleme sayfasına yönlenilir.
• Listeleme sayfasında Yarın Kapında alanında gelen il bilgisi ile anasayfada seçilen il bilgisinin
aynı olduğu kontrol edilir.
 */

public class SampleTest {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By konum = By.xpath("//div[contains(text(),'Konum seç')]");
        By il = By.xpath("//*[@id=\"shippingLocationFormArea\"]/div[3]/div/div[1]/div/div/div");
        By ilce = By.xpath("//*[@id=\"shippingLocationFormArea\"]/div[3]/div/div[2]");
        By mahalle = By.xpath("//*[@id=\"shippingLocationFormArea\"]/div[3]/div/div[3]/div");
        By mahalle_filtrele = By.xpath("/html/body/div[1]/div/div/div[3]/div[6]/div/div/div/div/div[2]/div[3]/div[1]/div/div/div/div/div/div/div/div/div[2]/div/div[3]/div/div[3]/div/div/div[2]/div[1]/div[2]/input");
        By mahalle_secimi = By.xpath("//div[contains(text(),'BAHÇEŞEHİR')]");
        By il_filtrele = By.xpath("//*[@id=\"shippingLocationFormArea\"]/div[3]/div/div[1]/div/div/div[2]/div[1]/div[2]/input");
        By il_secimi = By.xpath("//div[contains(text(),'KONYA')]");
        By ilce_filtrele = By.xpath("//*[@id=\"shippingLocationFormArea\"]/div[3]/div/div[2]/div/div/div[2]/div[1]/div[2]/input");
        By ilce_secimi = By.xpath("//div[contains(text(),'MERAM')]");
        By kaydet_buton = By.xpath("//button[contains(text(),'Kaydet')]");
        By kategori = By.xpath("//span[contains(text(),'Elektronik')]");
        By kategori_secimi = By.xpath("//a[contains(text(),'Bilgisayar/Tablet')]");
        By yarin_kapinda = By.xpath("//*[@id=\"JetDelivery\"]/div/div/div[2]/div[3]/div/div/div[1]/label/input");



        // Case 1: Uygulama açılır
        driver.manage().window().maximize();
        driver.get("https://www.hepsiburada.com/");
        Thread.sleep(9000);

        // Case 2: Anasayfada Konum alanına tıklanır.
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(konum)).click();

        // Case 3: • İl, ilçe ve mahalle seçilir ve kaydet butonuna Kulanır.
        // İl
        wait.until(ExpectedConditions.visibilityOfElementLocated(il)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(il_filtrele)).sendKeys("Konya");
        driver.findElement(il_secimi).click();
        // İlçe
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ilce)).click();
        driver.findElement(ilce_filtrele).sendKeys("Meram");
        driver.findElement(ilce_secimi).click();
        // Mahalle
        wait.until(ExpectedConditions.visibilityOfElementLocated(mahalle)).click();
        driver.findElement(mahalle_filtrele).sendKeys("Bahçeşehir");
        driver.findElement(mahalle_secimi).click();
        // Kaydet
        driver.findElement(kaydet_buton).click();

        // Case 4: Konumunuz Kaydedildi popup’ı kontrol edilir.
        Thread.sleep(500);
        WebElement assertElement = driver.findElement(By.xpath("//div[contains(text(),'Konum değiştirildi')]"));
        String assertText = assertElement.getText();
        Assert.assertEquals(assertText, "Konum değiştirildi");
        Assert.assertTrue(assertElement.isDisplayed());

        // Case 5: Tab bar üzerinden kategoriler tabına tıklanır.
        wait.until(ExpectedConditions.visibilityOfElementLocated(kategori)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(kategori_secimi)).click();
        js.executeScript("window.scrollTo(0,900)");

        // Case 6: Listeleme sayfasında Yarın Kapında alanında gelen il bilgisi ile anasayfada seçilen il bilgisinin
        //aynı olduğu kontrol edilir.
        // Yarın Kapında Click
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(yarin_kapinda)).click();
        // İl bilgisi kontrol
        WebElement assertElement2 = driver.findElement(By.xpath("//span[contains(text(),'KONYA')]"));
        String yarin_kapinda_il = assertElement2.getText();
        System.out.println(yarin_kapinda_il);
        Assert.assertEquals(yarin_kapinda_il, "KONYA");
        Assert.assertTrue(assertElement2.isDisplayed());

        Thread.sleep(2000);
        driver.quit();

    }


}

