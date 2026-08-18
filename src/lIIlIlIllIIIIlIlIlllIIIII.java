/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : port manuel du motion blur "phosphor" de polyblur (mixin d'origine
 * "cc.polyfrost.polyblur" visait des noms srg qui existent pas dans
 * l'obfuscation custom de ce jar donc injection auto par mixin impossible,
 * la logique est reportée à la main directement ici)
 *
 * cette classe = l'équivalent obfusqué de EntityRenderer vanilla (confirmé
 * via la signature updateCameraAndRender(float,long) et le champ theShaderGroup
 * qui gère déjà les shaders post nausea/creeper/spider/invert)
 *
 * ajouts :
 *  - champ polyBlurPhosphorShader : un 2e ShaderGroup séparé de theShaderGroup
 *    pour pas rentrer en conflit avec nausea/vision créature qui l'utilisent déjà
 *  - syncPolyBlurPhosphorState() lit l'état du module "motion blur" existant
 *    (lllIIIllIIllIIIlIlllIIIll id "motionBlur" déjà dans mods.json avant ce
 *    patch mais qui servait juste à afficher l'icone dans le menu mods aucune
 *    logique de rendu derrière) charge/décharge "shaders/post/
 *    phosphor_motion_blur.json" (fichiers copiés depuis le jar polyblur
 *    original, format vanilla standard) selon que le module est activé ou pas
 *  - resize suit updateShaderGroupSize (même point que createBindEntityOutlineFbs)
 *  - rendu injecté juste après renderEntityOutlineFramebuffer dans
 *    updateCameraAndRender comme le mixin polyblur d'origine
 *  - intensité pilotée via l'uniform "Weight" du shader lu en direct depuis le
 *    slider "Value" du module (1 à 10, défaut 1)
 *  - bug corrigé : écran totalement noir (menus compris) quand le module est
 *    actif ET que "fast render" d'optifine est activé -- optifine dit lui même
 *    dans son lang file "fast render is not compatible with shaders" et
 *    polyblur avertit pareil pour son mode phosphor, le shader group plante
 *    silencieusement le framebuffer principal dans ce cas donc le module est
 *    juste ignoré tant que fast render est activé (pas de plantage juste pas
 *    de flou, se réactive tout seul si fast render est désactivé en jeu)
 *
 * liste "could not load" d'origine via cfr :
 *  (classes internes obfusquées du jeu, résolues par le classpath au build)
 */
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import generated.Strings0;
import generated.Strings1;
import generated.Strings13;
import generated.Strings2;
import generated.Strings8;
import generated.Strings9;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Project;

public class lIIlIlIllIIIIlIlIlllIIIII
implements lIIIlIlIIlIllIlllIIlIllIl {
    public float IIIlIlIIlllllIIIlllIllIll;
    public int IllIlllIIIlIIllllllIllIII;
    public IlIIIIIlIIIIIIIlllIlIllII IIlllllllllIlIllIlIlIIllI;
    public IllIIIIIlIIIlllIIllIIIlIl IllllIllIIIlllIIllllllIII;
    public static boolean lllIllIllIlIIIlllIIllllII;
    public static lIIlIllIlIIllIIllIlIlllIl IlIllllIllllllllIIIlIIlII;
    public boolean IlIlIIIlIlIIlIIIlllIllIII;
    public lIIIIIIllIIIIIIIlllllllII llIIIIIIlllIlIIlIlIIllIII;
    public int IlIllIIlllllIllIIIlIIIlll;
    public int[] IlIlIIlIlIllIIlIlIIllIIIl;
    public float llIllllIlIllIlIlIIllIlIII;
    public double IlllIllIlIIIIIlllIlIIIIll;
    public float IlIIlIIlIlIIllllIIllIllll = 0.0f;
    public IIlIIllIIIIIlIllIIIIlllIl[] lIlIIIllllIlllIlIllllIlll;
    public static Logger lllIlIlllIIlIlIIlIlllIIlI;
    public IIlIIllIIIIIlIllIIIIlllIl polyBlurPhosphorShader = null;
    public int IIlIIIlIIlIlllIIllllIIIIl;
    public boolean IIlIlIlllllIllllIllllIllI;
    public boolean IIlIlIllllllIllllIIIIIllI;
    public float llIllllllIllllllllIllIIll;
    public static int llIIlIlIllIlIIIllIllllIlI;
    public float[] lIIllllllIIIIllllllIIIlll;
    public int lIIllIIlIIIllIlIIllIIlIll;
    public int IIIIlIllIlIIllIllIlIIlllI;
    public static lIIlIllIlIIllIIllIlIlllIl[] llllIllIllIIIlllIIlllIIll;
    public float IIlIIIllIIIlIlllIIIIllllI;
    public float IIIIlllIIIlIlIlIIIIIlllIl;
    public float llllIllIllIlIlIlIIIlIllll;
    public float llIIIllIIlIIIlIllIllIIlII;
    public lIllllIIIIIIlllIIllIIlIlI lllIllIIlllIllIlllIIlllII;
    public boolean lIIIlIIIlIllIlllIIIIIlIlI;
    public llIllllIllIIIlIIIllIlIlll IIIIlllllIIIllllllIlIllll = null;
    public FloatBuffer lllllIlIllIlIlllIIIlIIlIl;
    public float IlIllIIIIlIllllIlIIlIIlll;
    public float IIIIllIIIIIlIlIlllIIllIll;
    public boolean lIIllllIIlIIIllIlIlllIlII;
    public float lIlIIllIlIlIIlIlllIIllIII;
    public Random lIlIlIIIIllllIIlllllllIlI = new Random();
    public float IIIIIIllIlIIIIlIlllIllllI;
    public long IIIllllllIllIIIIIIIllIIll;
    public float IIIIIIllllIIlllIlllIIIlII = 0.0f;
    public boolean lIlllIIIlIIlIIIlIlIIIIlII;
    public float lIlIlIIIIlIlIlllIIIllllIl;
    public boolean IIlIIlIIlIlIllIlIllIlIIIl;
    public float IlIIlIIlIllIIIllllIIIlIIl;
    public llllIIlllIlIlIIlIIlllIIII lllIIllllIIlIIIlIIIIllIlI = new llllIIlllIlIlIIlIIlllIIII();
    public boolean lllIlIllllIlIIllIIIlIlllI;
    public long IllllIIlIllIIlIllIlIlIlIl;
    public float llIllIlIlIIIIlIIIIllIllll;
    public lIIlIllIlIIllIIllIlIlllIl lIIIlIIIIIIIlIIIllIIIlIII;
    public float lIllIllIIllIlllIIIlllIIIl;
    public float[] llIllIlllIllIlIIIIlIIlIII;
    public llllIIlllIlIlIIlIIlllIIII lllIllIllIIIIllIIlIIlIlll = new llllIIlllIlIlIIlIIlllIIII();
    public static int lIllllIIllIllllllIllIIIll;
    public IIlIIllIIIIIlIllIIIIlllIl lIlIlIllllIIlllIIIllllIlI;
    public float polyBlurFarPlaneDistance;
    public llIlllIIIllllIIlllIllIIIl IIIlIllIlIIlIlIIIlIlIlIll;
    public long IllIlllIIIlllllIllIIlIlIl;
    public double lIIIIIIllIllllIIlIIIllIIl;
    public boolean lIllllIlllllllllIllllIIll;
    public int IIlllIIIIIlllIllIIIllIlIl;
    public float lIIIlIllIlIlIlIlIIlIIIIIl;
    public float IIllIIIIIlIIIlIllIlIIllII;
    public float IIIllIIIlllIIIIlIlIIIIlIl;
    public static lIIlIllIlIIllIIllIlIlllIl IllIIllIlIlIlIlllIlIlIllI;
    public lllIIIIIlllIIlIllIIlIIIlI lllllIIIlIIllllllllIlllIl;
    public int IIlIIIIIIlllllllllIIIIIII;
    public long lIllIllllllllIlllIIllIIII;
    public float lIllIllllIllIlIIIllIIllll;
    public double llIllllllIIIIIIlIllIlIllI = 1.0;
    public int IIIIIlIIIllIllllIIlllIIII;

    static {
        lllIlIlllIIlIlIIlIlllIIlI = LogManager.getLogger();
        IlIllllIllllllllIIIlIIlII = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x2D6 & 0x56D0]);
        IllIIllIlIlIlIlllIlIlIllI = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x3D1 & 0xFFFFDAD5]);
        lIIlIllIlIIllIIllIlIlllIl[] lIIlIllIlIIllIIllIlIlllIlArray = new lIIlIllIlIIllIIllIlIlllIl[0x4D5D & 0x2038];
        lIIlIllIlIIllIIllIlIlllIlArray[0x1F04 & 0xFFFF8028] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x2D2 & 0x2DE]);
        lIIlIllIlIIllIIllIlIlllIlArray[0xFFFF8013 & 0x685] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x32DB & 0xFFFF82D3]);
        lIIlIllIlIIllIIllIlIlllIlArray[0xFFFFB442 & 0x82A] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x6AD4 & 0x3DC]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x2003 & 0xA2B] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x46D7 & 0x2D5]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x245 & 0x91C] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x7D6 & 0xADE]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x915 & 0x4265] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x42D7 & 0x22DF]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x4006 & 0xFFFF8D57] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x2DA & 0xFFFF86DC]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x402F & 0xFFFF9097] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x5AD9 & 0x3FB]);
        lIIlIllIlIIllIIllIlIlllIlArray[0xA & 0xFFFF8028] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x42DF & 0x3FA]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x879 & 0x4589] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x42FE & 0xFFFF82AE]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x414A & 0xFFFF802A] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x2DB & 0x22DF]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x604B & 0xAAB] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x42DD & 0x6DE]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x4C & 0x25BD] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x42DF & 0xFFFF82FD]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x68D & 0x280D] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0xFFFFD6FE & 0xBDE]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x2F & 0x40E] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x12DF & 0xFFFFA2DF]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x110F & 0x649F] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0xFFFF8BF0 & 0x6E8]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x1235 & 0x90] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x3E9 & 0x6E1]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x2B1 & 0x911] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x12E2 & 0xFFFF83E3]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x12 & 0x2033] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x72F3 & 0xFEB]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x6CB7 & 0x1013] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x1AE4 & 0xFFFFC6FF]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x12B4 & 0x241C] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x23E5 & 0xFFFFC2ED]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x397 & 0xFFFF8415] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x37F7 & 0xFFFF82E6]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x456 & 0x11BE] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0xBAC & 0xFFFF92EC]);
        lIIlIllIlIIllIIllIlIlllIlArray[0xFFFFA297 & 0x503F] = new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0xAAD & 0x43AD]);
        llllIllIllIIIlllIIlllIIll = lIIlIllIlIIllIIllIlIlllIlArray;
        lIllllIIllIllllllIllIIIll = llllIllIllIIIlllIIlllIIll.length;
    }

    public void lllIllIllIlIIIlllIIllllII(float f) {
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        if (lllIIIIIlllIIlIllIIlIIIlI2 != null && this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII != null) {
            double d;
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII(Strings13.strings[0x2BC & 0x7B0]);
            this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIlIllIlllIIIIIlIlI = null;
            double d2 = this.llIIIIIIlllIlIIlIlIIllIII.IllllIllIIIlllIIllllllIII.IlIllIIIIlIllllIlIIlIIlll();
            this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI = lllIIIIIlllIIlIllIIlIIIlI2.lllIllIllIlIIIlllIIllllII(d2, f);
            double d3 = d2;
            IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll = lllIIIIIlllIIlIllIIlIIIlI2.lIllllIIllIllllllIllIIIll(f);
            int n = 0x1F0 & 0xFFFF8401;
            int n2 = 0xD39 & 0xFFFF9205;
            if (this.llIIIIIIlllIlIIlIlIIllIII.IllllIllIIIlllIIllllllIII.lIIIlIIIlIllIlllIIIIIlIlI()) {
                d2 = 2.625 * 2.2857142857142856;
                d3 = 5.446153846153846 * 1.1016949152542372;
            } else if (d2 > 9.724137931034482 * 0.30851063829787234) {
                n = 0x1A01 & 0xFFFFC4E1;
            }
            if (this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI != null) {
                d3 = this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI.IllllIllIIIlllIIllllllIII.llIIIllIIlIIIlIllIllIIlII(ilIIIIIlllllllIIIIlllIIll);
            }
            IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll2 = lllIIIIIlllIIlIllIIlIIIlI2.IllllIllIIIlllIIllllllIII(f);
            IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll3 = ilIIIIIlllllllIIIIlllIIll.llIIlIlIllIlIIIllIllllIlI(ilIIIIIlllllllIIIIlllIIll2.lllIllIllIlIIIlllIIllllII * d2, ilIIIIIlllllllIIIIlllIIll2.llIIlIlIllIlIIIllIllllIlI * d2, ilIIIIIlllllllIIIIlllIIll2.IllllIllIIIlllIIllllllIII * d2);
            this.lllllIIIlIIllllllllIlllIl = null;
            IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll4 = null;
            float f2 = 1.0f;
            List list = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2, lllIIIIIlllIIlIllIIlIIIlI2.llIIllIllIIIlIIIllllIIlll().lllIllIllIlIIIlllIIllllII(ilIIIIIlllllllIIIIlllIIll2.lllIllIllIlIIIlllIIllllII * d2, ilIIIIIlllllllIIIIlllIIll2.llIIlIlIllIlIIIllIllllIlI * d2, ilIIIIIlllllllIIIIlllIIll2.IllllIllIIIlllIIllllllIII * d2).llIIlIlIllIlIIIllIllllIlI(f2, f2, f2), Predicates.and(lllIlIIllIIIIlIlIIIIIIlIl.IlIllIIIIlIllllIlIIlIIlll, new lIlIIlIIIllIIlIIlIIIIllIl(this)));
            double d4 = d3;
            for (int i = 0x4809 & 0xFFFF8040; i < list.size(); ++i) {
                double d5;
                lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI3 = (lllIIIIIlllIIlIllIIlIIIlI)list.get(i);
                float f3 = lllIIIIIlllIIlIllIIlIIIlI3.IlIllIIlllllIllIIIlIIIlll();
                IlIIIIIllIIlIIIlIllIIlIlI ilIIIIIllIIlIIIlIllIIlIlI = lllIIIIIlllIIlIllIIlIIIlI3.llIIllIllIIIlIIIllllIIlll().llIIlIlIllIlIIIllIllllIlI(f3, f3, f3);
                llIIIllllllIIllIllIIllIlI llIIIllllllIIllIllIIllIlI2 = ilIIIIIllIIlIIIlIllIIlIlI.lllIllIllIlIIIlllIIllllII(ilIIIIIlllllllIIIIlllIIll, ilIIIIIlllllllIIIIlllIIll3);
                if (ilIIIIIllIIlIIIlIllIIlIlI.lllIllIllIlIIIlllIIllllII(ilIIIIIlllllllIIIIlllIIll)) {
                    if (!(d4 >= 0.0)) continue;
                    this.lllllIIIlIIllllllllIlllIl = lllIIIIIlllIIlIllIIlIIIlI3;
                    ilIIIIIlllllllIIIIlllIIll4 = llIIIllllllIIllIllIIllIlI2 == null ? ilIIIIIlllllllIIIIlllIIll : llIIIllllllIIllIllIIllIlI2.IllllIllIIIlllIIllllllIII;
                    d4 = 0.0;
                    continue;
                }
                if (llIIIllllllIIllIllIIllIlI2 == null || !((d5 = ilIIIIIlllllllIIIIlllIIll.llIIIllIIlIIIlIllIllIIlII(llIIIllllllIIllIllIIllIlI2.IllllIllIIIlllIIllllllIII)) < d4) && d4 != 0.0) continue;
                int n3 = 0x6901 & 0xFFFF8240;
                if (lllIIIIIlllIIlIllIIlIIIlI3 == lllIIIIIlllIIlIllIIlIIIlI2.IIIIlllIIIlIlIlIIIIIlllIl && n3 == 0) {
                    if (d4 != 0.0) continue;
                    this.lllllIIIlIIllllllllIlllIl = lllIIIIIlllIIlIllIIlIIIlI3;
                    ilIIIIIlllllllIIIIlllIIll4 = llIIIllllllIIllIllIIllIlI2.IllllIllIIIlllIIllllllIII;
                    continue;
                }
                this.lllllIIIlIIllllllllIlllIl = lllIIIIIlllIIlIllIIlIIIlI3;
                ilIIIIIlllllllIIIIlllIIll4 = llIIIllllllIIllIllIIllIlI2.IllllIllIIIlllIIllllllIII;
                d4 = d5;
            }
            double d6 = d = this.lllllIIIlIIllllllllIlllIl == null ? 0.6842105263157895 * -1.4615384615384615 : ilIIIIIlllllllIIIIlllIIll.llIIIllIIlIIIlIllIllIIlII(ilIIIIIlllllllIIIIlllIIll4);
            if (this.lllllIIIlIIllllllllIlllIl != null && n != 0 && d > 5.0 * 0.6) {
                this.lllllIIIlIIllllllllIlllIl = null;
                this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI = new llIIIllllllIIllIllIIllIlI(lIlIllIIllIIIIlllIlIlllIl.lllIllIllIlIIIlllIIllllII, ilIIIIIlllllllIIIIlllIIll4, null, new llllIllIllllIlIlllllIlIlI(ilIIIIIlllllllIIIIlllIIll4));
            }
            if (this.lllllIIIlIIllllllllIlllIl != null && (d4 < d3 || this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI == null)) {
                this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI = new llIIIllllllIIllIllIIllIlI(this.lllllIIIlIIllllllllIlllIl, ilIIIIIlllllllIIIIlllIIll4);
                if (this.lllllIIIlIIllllllllIlllIl instanceof IllIlIIlIIllIlllIlIlIIlIl || this.lllllIIIlIIllllllllIlllIl instanceof llllIlIlIIllIlllllIlIlIlI) {
                    this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIlIllIlllIIIIIlIlI = this.lllllIIIlIIllllllllIlllIl;
                }
            }
            this.llIIIIIIlllIlIIlIlIIllIII.lllIllIllIIIIllIIlIIlIlll = d;
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.llIIlIlIllIlIIIllIllllIlI();
        }
    }

    public void lllIllIllIlIIIlllIIllllII(int n, float f) {
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        int n2 = 0x4220 & 0x803;
        this.lIIIlIIIlIllIlllIIIIIlIlI = false;
        if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IIllIIIllIIlIlllllIlllIII) {
            n2 = ((IIllIIIllIIlIlllllIlllIII)lllIIIIIlllIIlIllIIlIIIlI2).IllllIlllllIllIllIIIllllI.IlIllIIIIlIllllIlIIlIIlll ? 1 : 0;
        }
        GL11.glFog(0xFFFFCB67 & 0xBF6, this.lllIllIllIlIIIlllIIllllII(this.IlIllIIIIlIllllIlIIlIIlll, this.IIIlIlIIlllllIIIlllIllIll, this.llIIIllIIlIIIlIllIllIIlII, 1.0f));
        GL11.glNormal3f(0.0f, 6.4166665f * -0.15584417f, 0.0f);
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(1.0f, 1.0f, 1.0f, 1.0f);
        IllIIIIIIIlllIIIlIlIlIlll illIIIIIIIlllIIIlIlIlIlll = IIIlIlllIIlIIIlIllIIlIlll.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII, lllIIIIIlllIIlIllIIlIIIlI2, f);
        float f2 = -1.8518518f * 0.54f;
        if (f2 >= 0.0f) {
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(f2);
        } else if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IIIlIllIlIIlIlIIIlIlIlIll)) {
            float f3 = 12.599999f * 0.3968254f;
            int n3 = ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).llIIlIlIllIlIIIllIllllIlI(lIIIlllIllIIIlIlIIllIIlIl.IIIlIllIlIIlIlIIIlIlIlIll).llIIlIlIllIlIIIllIllllIlI();
            if (n3 < (0x4234 & 0x4DC)) {
                f3 = 0.64285713f * 7.7777777f + (this.llIllllllIllllllllIllIIll - 0.75555557f * 6.6176467f) * (1.0f - (float)n3 / (12.0f * 1.6666666f));
            }
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                IlllIllIIIlIIIlllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(0x3669 & 0x2605);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll(0x2645 & 0x2609);
            }
            if (n == (0xFFFFFFFF & 0xFFFFFFFF)) {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f);
                lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f3 * (1.2682927f * 0.63076925f));
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f3 * (0.65909094f * 0.37931034f));
                lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f3);
            }
            if (GLContext.getCapabilities().GL_NV_fog_distance && IIIIllIlIIIllIIIIIIlIlIll.lllllIIIlIIllllllllIlllIl()) {
                GL11.glFogi(0x2068F5A & 0xC401A57A, 0x42108D5B & 0x2068C75B);
            }
        } else if (this.lllIlIllllIlIIllIIIlIlllI) {
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                IlllIllIIIlIIIlllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(0x2852 & 0x824);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll(0x6B80 & 0x840);
            }
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0.28285715f * 0.35353535f);
        } else if (illIIIIIIIlllIIIlIlIlIlll.llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lIIllIIlIIIllIlIIllIIlIll) {
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                IlllIllIIIlIIIlllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(0xFFFF8800 & 0x2A2A);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll(0xFFFF8800 & 0x2821);
            }
            if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.lIlIlIIIIllllIIlllllllIlI)) {
                lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0.46153846f * 0.021666666f);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0.11463415f * 0.87234044f - (float)lIIIlllIlIIIlIlIllIllIlll.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2) * (1.2307693f * 0.024375f));
            }
            if (IIIIllIlIIIllIIIIIIlIlIll.IIllIlIllIlIllIllIllIllII()) {
                lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0.011842105f * 1.6888889f);
            }
        } else if (illIIIIIIIlllIIIlIlIlIlll.llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lIIIlIIIlIllIlllIIIIIlIlI) {
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                IlllIllIIIlIIIlllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(0xD60 & 0xFFFFFA00);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll(0xC00 & 0x5841);
            }
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(2.0f);
        } else {
            float f4 = this.llIllllllIllllllllIllIIll;
            this.lIIIlIIIlIllIlllIIIIIlIlI = true;
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                IlllIllIIIlIIIlllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(0xFFFFEE11 & 0x3607);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll(0x6E21 & 0xFFFFA653);
            }
            if (n == (0xFFFFFFFF & 0xFFFFFFFF)) {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f);
                lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f4);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f4 * IIIIllIlIIIllIIIIIIlIlIll.lIlIIllIlIlIIlIlllIIllIII());
                lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f4);
            }
            if (GLContext.getCapabilities().GL_NV_fog_distance) {
                if (IIIIllIlIIIllIIIIIIlIlIll.lllllIIIlIIllllllllIlllIl()) {
                    GL11.glFogi(0x20009D7E & 0x100C5DA, 0x1860E75F & 0x2508857B);
                }
                if (IIIIllIlIIIllIIIIIIlIlIll.lllIIllllIIlIIIlIIIIllIlI()) {
                    GL11.glFogi(0x5E108DDB & 0x8185C55A, 0x1010C5DC & 0xA1088D5F);
                }
            }
            if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.llIllllllIllllllllIllIIll.llIIlIlIllIlIIIllIllllIlI((int)lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll, (int)lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII)) {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f4 * (0.101388894f * 0.49315068f));
                lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f4);
            }
        }
        lIIllIlIIlllIlIlIlllIlIlI.lIllllIIllIllllllIllIIIll();
        lIIllIlIIlllIlIlIlllIlIlI.llIIIIIIlllIlIIlIlIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x2505 & 0xFFFF8406, 0x1610 & 0x1201);
    }

    public void IIlllllllllIlIllIlIlIIllI() {
        float f = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(1.0f);
        if (!IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIlIlIlllIIIllllIl()) {
            f /= 2.0f;
        }
        if (f != 0.0f && !((Boolean)IlIIllIIllIIIllllIIllIIII.llIIlIlIllIlIIIllIllllIlI().lIIIlIIIlIllIlllIIIIIlIlI().IllllIllIIIlllIIllllllIII().lIllIllllIllIlIIIllIIllll().IIlllllllllIlIllIlIlIIllI()).booleanValue() && IIIIllIlIIIllIIIIIIlIlIll.lllllIlIllIlIlllIIIlIIlIl()) {
            this.lIlIlIIIIllllIIlllllllIlI.setSeed((long)this.IIlIIIIIIlllllllllIIIIIII * (0xBE52167FDFB7CEDFL & 0x41ADE98012E7EE5FL));
            lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
            IllIlIlIllllIllIIIllIIlII illIlIlIllllIllIIIllIIlII = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII;
            llllIllIllllIlIlllllIlIlI llllIllIllllIlIlllllIlIlI2 = new llllIllIllllIlIlllllIlIlI(lllIIIIIlllIIlIllIIlIIIlI2);
            int n = 0x240A & 0x8E;
            double d = 0.0;
            double d2 = 0.0;
            double d3 = 0.0;
            int n2 = 0xB06 & 0xFFFFA081;
            int n3 = (int)(3.7692308f * 26.530611f * f * f);
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.llIIllIllIIIlIIIllllIIlll == (0x37B5 & 0xB)) {
                n3 >>= 0x411 & 0xFFFFE881;
            } else if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.llIIllIllIIIlIIIllllIIlll == (0xFFFFA013 & 0x4EAA)) {
                n3 = 0xFFFF8011 & 0x1A02;
            }
            for (int i = 0x1684 & 0xFFFF810A; i < n3; ++i) {
                llllIllIllllIlIlllllIlIlI llllIllIllllIlIlllllIlIlI3 = illIlIlIllllIllIIIllIIlII.IIIlIllIlIIlIlIIIlIlIlIll(llllIllIllllIlIlllllIlIlI2.lllIllIllIlIIIlllIIllllII(this.lIlIlIIIIllllIIlllllllIlI.nextInt(n) - this.lIlIlIIIIllllIIlllllllIlI.nextInt(n), 0x4801 & 0x3112, this.lIlIlIIIIllllIIlllllllIlI.nextInt(n) - this.lIlIlIIIIllllIIlllllllIlI.nextInt(n)));
                IIIIlllIIIlIIIIllllIlIlII iIIIlllIIIlIIIIllllIlIlII = illIlIlIllllIllIIIllIIlII.IllllIllIIIlllIIllllllIII(llllIllIllllIlIlllllIlIlI3);
                llllIllIllllIlIlllllIlIlI llllIllIllllIlIlllllIlIlI4 = llllIllIllllIlIlllllIlIlI3.llIIlIlIllIlIIIllIllllIlI();
                IllIIIIIIIlllIIIlIlIlIlll illIIIIIIIlllIIIlIlIlIlll = illIlIlIllllIllIIIllIIlII.llIIlIlIllIlIIIllIllllIlI(llllIllIllllIlIlllllIlIlI4).IllllIllIIIlllIIllllllIII();
                if (llllIllIllllIlIlllllIlIlI3.lIIIlIIIlIllIlllIIIIIlIlI() > llllIllIllllIlIlllllIlIlI2.lIIIlIIIlIllIlllIIIIIlIlI() + n || llllIllIllllIlIlllllIlIlI3.lIIIlIIIlIllIlllIIIIIlIlI() < llllIllIllllIlIlllllIlIlI2.lIIIlIIIlIllIlllIIIIIlIlI() - n || !iIIIlllIIIlIIIIllllIlIlII.IIIlIlIIlllllIIIlllIllIll() || !(iIIIlllIIIlIIIIllllIlIlII.lllIllIllIlIIIlllIIllllII(llllIllIllllIlIlllllIlIlI3) >= 0.8372093f * 0.17916667f)) continue;
                double d4 = this.lIlIlIIIIllllIIlllllllIlI.nextDouble();
                double d5 = this.lIlIlIIIIllllIIlllllllIlI.nextDouble();
                if (illIIIIIIIlllIIIlIlIlIlll.llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lIIIlIIIlIllIlllIIIIIlIlI) {
                    this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(IlIllIllIIIlIIIIlIIIlIlIl.IllIIllIlIlIlIlllIlIlIllI, (double)llllIllIllllIlIlllllIlIlI3.lIIllIIlIIIllIlIIllIIlIll() + d4, (double)((float)llllIllIllllIlIlllllIlIlI3.lIIIlIIIlIllIlllIIIIIlIlI() + 1.2571429f * 0.07954545f) - illIIIIIIIlllIIIlIlIlIlll.lllIIllllIIlIIIlIIIIllIlI(), (double)llllIllIllllIlIlllllIlIlI3.lllIlIlllIIlIlIIlIlllIIlI() + d5, 0.0, 0.0, 0.0, new int[0x760C & 0x62]);
                    continue;
                }
                if (illIIIIIIIlllIIIlIlIlIlll.llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lllIllIllIlIIIlllIIllllII) continue;
                illIIIIIIIlllIIIlIlIlIlll.IllllIllIIIlllIIllllllIII((lllIIllllIlIlIlllIIIIIlll)illIlIlIllllIllIIIllIIlII, llllIllIllllIlIlllllIlIlI4);
                if (this.lIlIlIIIIllllIIlllllllIlI.nextInt(++n2) == 0) {
                    d = (double)llllIllIllllIlIlllllIlIlI4.lIIllIIlIIIllIlIIllIIlIll() + d4;
                    d2 = (double)((float)llllIllIllllIlIlllllIlIlI4.lIIIlIIIlIllIlllIIIIIlIlI() + 10.222222f * 0.009782609f) + illIIIIIIIlllIIIlIlIlIlll.lllIllIllIIIIllIIlIIlIlll() - 1.0;
                    d3 = (double)llllIllIllllIlIlllllIlIlI4.lllIlIlllIIlIlIIlIlllIIlI() + d5;
                }
                this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(IlIllIllIIIlIIIIlIIIlIlIl.lIIIlIIIIIIIlIIIllIIIlIII, (double)llllIllIllllIlIlllllIlIlI4.lIIllIIlIIIllIlIIllIIlIll() + d4, (double)((float)llllIllIllllIlIlllllIlIlI4.lIIIlIIIlIllIlllIIIIIlIlI() + 0.5277778f * 0.18947369f) + illIIIIIIIlllIIIlIlIlIlll.lllIllIllIIIIllIIlIIlIlll(), (double)llllIllIllllIlIlllllIlIlI4.lllIlIlllIIlIlIIlIlllIIlI() + d5, 0.0, 0.0, 0.0, new int[0x4D04 & 0xFFFF8092]);
            }
            if (n2 > 0) {
                int n4 = this.IIlIIIlIIlIlllIIllllIIIIl;
                this.IIlIIIlIIlIlllIIllllIIIIl = n4 + (0x401 & 0xFFFF9A05);
                if (this.lIlIlIIIIllllIIlllllllIlI.nextInt(0xFFFF9A63 & 0x493) < n4) {
                    this.IIlIIIlIIlIlllIIllllIIIIl = 0x1A10 & 0x2446;
                    if (d2 > (double)(llllIllIllllIlIlllllIlIlI2.lIIIlIIIlIllIlllIIIIIlIlI() + (0xC83 & 0x3331)) && illIlIlIllllIllIIIllIIlII.IIIlIllIlIIlIlIIIlIlIlIll(llllIllIllllIlIlllllIlIlI2).lIIIlIIIlIllIlllIIIIIlIlI() > llIllIIlIllllllIlllIlIlIl.IlIllIIIIlIllllIlIIlIIlll((float)llllIllIllllIlIlllllIlIlI2.lIIIlIIIlIllIlllIIIIIlIlI())) {
                        this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(d, d2, d3, Strings13.strings[0xFFFF8AE7 & 0x13CD], 1.09375f * 0.09142857f, 3.2068965f * 0.15591398f, (0x2212 & 0xFFFFD880) != 0);
                    } else {
                        this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(d, d2, d3, Strings13.strings[0x3D5 & 0x6EC5], 0.6363636f * 0.31428573f, 1.0f, (0x1530 & 0xA40) != 0);
                    }
                }
            }
        }
    }

    public void IlIllIIIIlIllllIlIIlIIlll(float f) {
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() instanceof IIllIIIllIIlIlllllIlllIII) {
            IIllIIIllIIlIlllllIlllIII iIllIIIllIIlIlllllIlllIII = (IIllIIIllIIlIlllllIlllIII)this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
            float f2 = iIllIIIllIIlIlllllIlllIII.IIIIIlIIIllIllllIIlllIIII - iIllIIIllIIlIlllllIlllIII.lIllIllIIllIlllIIIlllIIIl;
            float f3 = -(iIllIIIllIIlIlllllIlllIII.IIIIIlIIIllIllllIIlllIIII + f2 * f);
            float f4 = iIllIIIllIIlIlllllIlllIII.IlllIlIlIlIIIllIIllIlIIII + (iIllIIIllIIlIlllllIlllIII.IIIIIIllllllIIIIlIlIIlIlI - iIllIIIllIIlIlllllIlllIII.IlllIlIlIlIIIllIIllIlIIII) * f;
            float f5 = iIllIIIllIIlIlllllIlllIII.lIllllllIIlIIllIIllllIlll + (iIllIIIllIIlIlllllIlllIII.lllIIIlllllIIlIIlIllllIII - iIllIIIllIIlIlllllIlllIII.lIllllllIIlIIllIIllllIlll) * f;
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(f3 * (0.5770272f * 5.4444447f)) * f4 * (2.6842105f * 0.18627451f), -Math.abs(llIllIIlIllllllIlllIlIlIl.llIIlIlIllIlIIIllIllllIlI(f3 * (2.19533f * 1.4310344f)) * f4), 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(f3 * (0.10843374f * 28.972466f)) * f4 * (1.7027028f * 1.7619047f), 0.0f, 0.0f, 1.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(Math.abs(llIllIIlIllllllIlllIlIlIl.llIIlIlIllIlIIIllIllllIlI(f3 * (23.682777f * 0.13265306f) - 4.0f * 0.05f) * f4) * (0.11363637f * 44.0f), 1.0f, 0.0f, 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f5, 1.0f, 0.0f, 0.0f);
        }
    }

    public void IIIlIllIlIIlIlIIIlIlIlIll() {
        long l;
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII != null && (l = System.currentTimeMillis()) > this.IllIlllIIIlllllIllIIlIlIl + (0x12A711L & 0x19002730L)) {
            this.IllIlllIIIlllllIllIIlIlIl = l;
            int n = GL11.glGetError();
            if (n != 0) {
                String string = GLU.gluErrorString(n);
                Object[] objectArray = new Object[0xFFFF810A & 0x1C17];
                objectArray[0xFFFF8014 & 0x3648] = n;
                objectArray[0x4821 & 0xFFFFB485] = string;
                IIIllIlIIlIIIIIlIlIIlllII iIIllIlIIlIIIIIlIlIIlllII = new IIIllIlIIlIIIIIlIlIIlllII(IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings13.strings[0xFFFFB2CF & 0x2CB], objectArray));
                this.llIIIIIIlllIlIIlIlIIllIII.IIlIIIIIIlllllllllIIIIIII.IlIllIIIIlIllllIlIIlIIlll().lllIllIllIlIIIlllIIllllII(iIIllIlIIlIIIIIlIlIIlllII);
            }
        }
    }

    public void lllIllIllIlIIIlllIIllllII(float f, int n, boolean bl, boolean bl2, boolean bl3) {
        if (!this.IIlIlIlllllIllllIllllIllI) {
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x7701 & 0x1F13);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            float f2 = 0.14583334f * 0.48f;
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll) {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI((float)(-(n * (0xFFFF828F & 0x6942) - (0x2AA1 & 0x45))) * f2, 0.0f, 0.0f);
            }
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                IlllIllIIIlIIIlllIllIIlII.lIIllllIIlIIIllIlIlllIlII();
            }
            Project.gluPerspective(this.lllIllIllIlIIIlllIIllllII(f, (0x114A & 0x6A0) != 0), (float)this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll / (float)this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll, 34.0f * 0.0014705883f, this.llIllllllIllllllllIllIIll * 2.0f);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1709 & 0x1716);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll) {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI((float)(n * (0x12 & 0x5527) - (0x601 & 0x3011)) * (0.3493976f * 0.2862069f), 0.0f, 0.0f);
            }
            int n2 = 0x421 & 0xFFFF9258;
            if (bl) {
                lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
                this.IllllIllIIIlllIIllllllIII(f);
                if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIllIIIIlIllllIlIIlIIlll) {
                    this.IlIllIIIIlIllllIlIIlIIlll(f);
                }
                int n3 = n2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI()).lIlllllIIIIIlllllIIlIllII() ? 0xFFFFC121 & 0x3E47 : 0x4000 & 0xFFFF842A;
                if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIIIlIllIlllIIIIIIlllII == 0 && n2 == 0 && !this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIIIIlIIIlllIllIIllIllll && !this.llIIIIIIlllIlIIlIlIIllIII.IllllIllIIIlllIIllllllIII.lllIllIllIlIIIlllIIllllII()) {
                    this.lIIllIIlIIIllIlIIllIIlIll();
                    if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
                        llIlIIIlIIIlIIIIllllIlIII.lllIllIllIlIIIlllIIllllII(this.IllllIllIIIlllIIllllllIII, f, bl3);
                    } else {
                        this.IllllIllIIIlllIIllllllIII.lllIllIllIlIIIlllIIllllII(f);
                    }
                    this.lIllllIIllIllllllIllIIIll();
                }
                lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            }
            if (!bl2) {
                return;
            }
            this.lIllllIIllIllllllIllIIIll();
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIIIlIllIlllIIIIIIlllII == 0 && n2 == 0) {
                this.IllllIllIIIlllIIllllllIII.llIIlIlIllIlIIIllIllllIlI(f);
                this.IllllIllIIIlllIIllllllIII(f);
            }
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIllIIIIlIllllIlIIlIIlll) {
                this.IlIllIIIIlIllllIlIIlIIlll(f);
            }
        }
    }

    public void lIlIlIIIIllllIIlllllllIlI() {
        this.IllIlllIIIlIIllllllIllIII = 0x2028 & 0x1481;
        if (IIIIllIlIIIllIIIIIIlIlIll.lIllIlIlIlIlIIIllllIlIlII() && IIIIllIlIIIllIIIIIIlIlIll.IlllIIlIllllIlllIIlIIlIII()) {
            llllIIIlllIIllllllIlIlIll llllIIIlllIIllllllIlIlIll2;
            if (this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll() && (llllIIIlllIIllllllIlIlIll2 = this.llIIIIIIlllIlIIlIlIIllIII.IIIllIIIlllIIIIlIlIIIIlIl()) != null) {
                boolean bl = this.llIIIIIIlllIlIIlIlIIllIII.lIIllllllIIIIllllllIIIlll();
                if (!bl && !(this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII instanceof IIIIllllllIlIlIIllIlIIllI)) {
                    if (this.IlIllIIlllllIllIIIlIIIlll > 0) {
                        IllIIlllIlllIIlIlIIIIIlll.lIIllIIlIIIllIlIIllIIlIll.lllIllIllIlIIIlllIIllllII();
                        IIIIllIlIIIllIIIIIIlIlIll.lllIllIllIlIIIlllIIllllII((long)this.IlIllIIlllllIllIIIlIIIlll);
                        IllIIlllIlllIIlIlIIIIIlll.lIIllIIlIIIllIlIIllIIlIll.llIIlIlIllIlIIIllIllllIlI();
                        this.IllIlllIIIlIIllllllIllIII = this.IlIllIIlllllIllIIIlIIIlll;
                    }
                    long l = System.nanoTime() / (0xB0F7240L & 0xB15383A2702FC241L);
                    if (this.IIIllllllIllIIIIIIIllIIll != (0xE2E448D120240137L & 0x8820200L) && this.IIlllIIIIIlllIllIIIllIlIl != 0) {
                        long l2 = l - this.IIIllllllIllIIIIIIIllIIll;
                        if (l2 < (0x114002C1L & 0x60244022L)) {
                            this.IIIllllllIllIIIIIIIllIIll = l;
                            l2 = 0x208000A2L & 0x4E65A158L;
                        }
                        if (l2 >= (0x41B5DF8C8540093AL & 0x3AA00237L)) {
                            this.IIIllllllIllIIIIIIIllIIll = l;
                            int n = llllIIIlllIIllllllIlIlIll2.IlIIlIIlIlIIllllIIllIllll();
                            int n2 = n - this.IIlllIIIIIlllIllIIIllIlIl;
                            if (n2 < 0) {
                                this.IIlllIIIIIlllIllIIIllIlIl = n;
                                n2 = 0x28AB & 0xFFFFC004;
                            }
                            if (n2 < (0x408D & 0x3163) && this.IlIllIIlllllIllIIIlIIIlll < (0xC64 & 0xFFFF90E7)) {
                                this.IlIllIIlllllIllIIIlIIIlll += 0xFFFFC587 & 0xA42;
                            }
                            if (n2 > (0x5401 & 0xB61) && this.IlIllIIlllllIllIIIlIIIlll > 0) {
                                this.IlIllIIlllllIllIIIlIIIlll -= 0xFFFFA807 & 0x41;
                            }
                            this.IIlllIIIIIlllIllIIIllIlIl = n;
                        }
                    } else {
                        this.IIIllllllIllIIIIIIIllIIll = l;
                        this.IIlllIIIIIlllIllIIIllIlIl = llllIIIlllIIllllllIlIlIll2.IlIIlIIlIlIIllllIIllIllll();
                        this.IIIIIIllllIIlllIlllIIIlII = 1.0f;
                        this.IlIIlIIlIlIIllllIIllIllll = 0.7979798f * 62.65823f;
                    }
                } else {
                    if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII instanceof IIIIllllllIlIlIIllIlIIllI) {
                        IIIIllIlIIIllIIIIIIlIlIll.lllIllIllIlIIIlllIIllllII(0x1407F4L & 0x43030816L);
                    }
                    this.IIIllllllIllIIIIIIIllIIll = 0xC82579D3280011C1L & 0x37DA862C80A52200L;
                    this.IIlllIIIIIlllIllIIIllIlIl = 0x29 & 0x6354;
                }
            }
        } else {
            this.IIIllllllIllIIIIIIIllIIll = 0x12A00100L & 0xD826506788475E49L;
            this.IIlllIIIIIlllIllIIIllIlIl = 0x2C4 & 0x402A;
        }
    }

    public void IlIllIIIIlIllllIlIIlIIlll() {
        if (IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI && this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() instanceof IIllIIIllIIlIlllllIlllIII) {
            if (this.lIlIlIllllIIlllIIIllllIlI != null) {
                this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII();
            }
            this.IIIIlIllIlIIllIllIlIIlllI = (this.IIIIlIllIlIIllIllIlIIlllI + (0x30F1 & 0x607)) % (llllIllIllIIIlllIIlllIIll.length + (0xFFFFA241 & 0x29));
            if (this.IIIIlIllIlIIllIllIlIIlllI != lIllllIIllIllllllIllIIIll) {
                this.llIIlIlIllIlIIIllIllllIlI(llllIllIllIIIlllIIlllIIll[this.IIIIlIllIlIIllIllIlIIlllI]);
            } else {
                this.lIlIlIllllIIlllIIIllllIlI = null;
            }
        }
    }

    public void llIIlIlIllIlIIIllIllllIlI() {
        if (this.lIlIlIllllIIlllIIIllllIlI != null) {
            this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII();
        }
        this.lIlIlIllllIIlllIIIllllIlI = null;
        this.IIIIlIllIlIIllIllIlIIlllI = lIllllIIllIllllllIllIIIll;
    }

    public void lIIIlIIIlIllIlllIIIIIlIlI() {
        IIIllllIIIllIIIlIllIIllIl iIIllllIIIllIIIlIllIIllIl = new IIIllllIIIllIIIlIllIIllIl(this.llIIIIIIlllIlIIlIlIIllIII);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII(0xFFFFE34A & 0x11A1);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1793 & 0x3745);
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0.0, iIIllllIIIllIIIlIllIIllIl.IllllIllIIIlllIIllllllIII(), iIIllllIIIllIIIlIllIIllIl.IlIllIIIIlIllllIlIIlIIlll(), 0.0, 709.6774193548387 * 1.4090909090909092, 0.6979166666666666 * 4298.507462686567);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0xFFFF9734 & 0x5F00);
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, 0.0f, -3599.9998f * 0.5555556f);
    }

    public void llIIlIlIllIlIIIllIllllIlI(lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2) {
        if (IIlllIIlIllIIIIllIlIIIlll.lIIIlIIIlIllIlllIIIIIlIlI()) {
            try {
                this.lIlIlIllllIIlllIIIllllIlI = new IIlIIllIIIIIlIllIIIIlllIl(this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII(), this.IIlllllllllIlIllIlIlIIllI, this.llIIIIIIlllIlIIlIlIIllIII.llIIlIlIllIlIIIllIllllIlI(), lIIlIllIlIIllIIllIlIlllIl2);
                this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll, this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll);
                this.IIlIIlIIlIlIllIlIllIlIIIl = true;
            }
            catch (JsonSyntaxException jsonSyntaxException) {
                Object[] objectArray = new Object[0x112B & 0x855];
                objectArray[0x6486 & 0xFFFF8110] = jsonSyntaxException;
                IIIIlIllllIIlIIIIlIlIIIII.IllllIllIIIlllIIllllllIII((Object)(Strings9.strings[0x20D5 & 0x1E75] + lIIlIllIlIIllIIllIlIlllIl2 + Strings13.strings[0x2AF & 0xFFFF8AAF]), objectArray);
                this.IIIIlIllIlIIllIllIlIIlllI = lIllllIIllIllllllIllIIIll;
                this.IIlIIlIIlIlIllIlIllIlIIIl = false;
            }
        }
    }

    public boolean lllIllIllIlIIIlllIIllllII(int n) {
        if (!IIlllIIlIllIIIIllIlIIIlll.lIIIlIIIlIllIlllIIIIIlIlI()) {
            return (0xFFFF8C0A & 0x2C1) != 0;
        }
        if (this.lIlIlIllllIIlllIIIllllIlI != null && this.lIlIlIllllIIlllIIIllllIlI != this.lIlIIIllllIlllIlIllllIlll[0xFFFF8197 & 0x442] && this.lIlIlIllllIIlllIIIllllIlI != this.lIlIIIllllIlllIlIllllIlll[0x3424 & 0xFFFF8096]) {
            return (0x4045 & 0xFFFF8129) != 0;
        }
        if (n != (0xFFFF809B & 0x3242) && n != (0x240C & 0x4206)) {
            if (this.lIlIlIllllIIlllIIIllllIlI == null) {
                return (0x3349 & 0x491) != 0;
            }
            this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII();
            this.lIlIlIllllIIlllIIIllllIlI = null;
            return (0x1885 & 0xFFFFC351) != 0;
        }
        if (this.lIlIlIllllIIlllIIIllllIlI != null && this.lIlIlIllllIIlllIIIllllIlI == this.lIlIIIllllIlllIlIllllIlll[n]) {
            return (0xA53 & 0xA1) != 0;
        }
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII == null) {
            return (0x1215 & 0xFFFF8809) != 0;
        }
        this.llIIlIlIllIlIIIllIllllIlI(new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x23CE & 0x16CF] + n + Strings13.strings[0xFFFFC2CF & 0xAEF]));
        this.lIlIIIllllIlllIlIllllIlll[n] = this.lIlIlIllllIIlllIIIllllIlI;
        return this.IIlIIlIIlIlIllIlIllIlIIIl;
    }

    public void llIIlIlIllIlIIIllIllllIlI(float f, int n) {
        this.lllIllIllIlIIIlllIIllllII(f, n, (0xFFFF8047 & 0x24B9) != 0, (0xFFFF81B1 & 0x5A07) != 0, (0x4040 & 0x1B32) != 0);
    }

    public void llIIlIlIllIlIIIllIllllIlI(float f) {
        float f2 = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(f);
        if (f2 > 0.0f) {
            if (IIIIllIlIIIllIIIIIIlIlIll.lIllIllllIllIlIIIllIIllll()) {
                return;
            }
            this.lIIllIIlIIIllIlIIllIIlIll();
            lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
            IllIlIlIllllIllIIIllIIlII illIlIlIllllIllIIIllIIlII = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII;
            int n = llIllIIlIllllllIlllIlIlIl.IllllIllIIIlllIIllllllIII(lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll);
            int n2 = llIllIIlIllllllIlllIlIlIl.IllllIllIIIlllIIllllllIII(lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl);
            int n3 = llIllIIlIllllllIlllIlIlIl.IllllIllIIIlllIIllllllIII(lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII);
            IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
            lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
            lIIllIlIIlllIlIlIlllIlIlI.llIllllllIllllllllIllIIll();
            GL11.glNormal3f(0.0f, 1.0f, 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x6BE6 & 0xFFFF8302, 0x13E3 & 0x670F, 0x4101 & 0x2081, 0x230 & 0x28C1);
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0xA94 & 0x3224, 1.451613f * 0.06888889f);
            double d = lllIIIIIlllIIlIllIIlIIIlI2.lIIIIIIllIllllIIlIIIllIIl + (lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll - lllIIIIIlllIIlIllIIlIIIlI2.lIIIIIIllIllllIIlIIIllIIl) * (double)f;
            double d2 = lllIIIIIlllIIlIllIIlIIIlI2.IlllIllIlIIIIIlllIlIIIIll + (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl - lllIIIIIlllIIlIllIIlIIIlI2.IlllIllIlIIIIIlllIlIIIIll) * (double)f;
            double d3 = lllIIIIIlllIIlIllIIlIIIlI2.lIlIlIllllIIlllIIIllllIlI + (lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII - lllIIIIIlllIIlIllIIlIIIlI2.lIlIlIllllIIlllIIIllllIlI) * (double)f;
            int n4 = llIllIIlIllllllIlllIlIlIl.IllllIllIIIlllIIllllllIII(d2);
            int n5 = 0xFFFF8407 & 0x1805;
            if (IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIlIlIlllIIIllllIl()) {
                n5 = 0x2A5B & 0xFFFF800A;
            }
            int n6 = 0xFFFFFFFF & 0xFFFFFFFF;
            float f3 = (float)this.IIlIIIIIIlllllllllIIIIIII + f;
            lIIIllIlIIIlIllIlIIllllIl2.IllllIllIIIlllIIllllllIII(-d, -d2, -d3);
            if (IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIlIlIlllIIIllllIl()) {
                n5 = 0xE4A & 0x200A;
            }
            lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(1.0f, 1.0f, 1.0f, 1.0f);
            IllIIIllIlIIlIllIIIllllIl illIIIllIlIIlIllIIIllllIl = new IllIIIllIlIIlIllIIIllllIl();
            for (int i = n3 - n5; i <= n3 + n5; ++i) {
                for (int j = n - n5; j <= n + n5; ++j) {
                    double d4;
                    double d5;
                    double d6;
                    int n7 = (i - n3 + (0x4610 & 0x21B9)) * (0x2274 & 0xFFFFDDA9) + j - n + (0x18 & 0x2056);
                    double d7 = (double)this.llIllIlllIllIlIIIIlIIlIII[n7] * (0.43999999999999995 * 1.1363636363636365);
                    double d8 = (double)this.lIIllllllIIIIllllllIIIlll[n7] * (0.6176470588235294 * 0.8095238095238095);
                    illIIIllIlIIlIllIIIllllIl.llIIlIlIllIlIIIllIllllIlI(j, 0xFFFFAF82 & 0x1044, i);
                    IIIIlllIIIlIIIIllllIlIlII iIIIlllIIIlIIIIllllIlIlII = illIlIlIllllIllIIIllIIlII.IllllIllIIIlllIIllllllIII(illIIIllIlIIlIllIIIllllIl);
                    if (!iIIIlllIIIlIIIIllllIlIlII.IIIlIlIIlllllIIIlllIllIll() && !iIIIlllIIIlIIIIllllIlIlII.IlIllIIIIlIllllIlIIlIIlll()) continue;
                    int n8 = illIlIlIllllIllIIIllIIlII.IIIlIllIlIIlIlIIIlIlIlIll(illIIIllIlIIlIllIIIllllIl).lIIIlIIIlIllIlllIIIIIlIlI();
                    int n9 = n2 - n5;
                    int n10 = n2 + n5;
                    if (n9 < n8) {
                        n9 = n8;
                    }
                    if (n10 < n8) {
                        n10 = n8;
                    }
                    int n11 = n8;
                    if (n8 < n4) {
                        n11 = n4;
                    }
                    if (n9 == n10) continue;
                    this.lIlIlIIIIllllIIlllllllIlI.setSeed(j * j * (0x5CF1 & 0xC3B) + j * (0xAB3DAFF & 0x2B64BBB) ^ i * i * (0x8A0667B7 & 0x64077397) + i * (0x35CD & 0xFFFFBDF3));
                    illIIIllIlIIlIllIIIllllIl.llIIlIlIllIlIIIllIllllIlI(j, n9, i);
                    float f4 = iIIIlllIIIlIIIIllllIlIlII.lllIllIllIlIIIlllIIllllII(illIIIllIlIIlIllIIIllllIl);
                    if (illIlIlIllllIllIIIllIIlII.lllIlIlllIIlIlIIlIlllIIlI().lllIllIllIlIIIlllIIllllII(f4, n8) >= 0.0664557f * 2.2571428f) {
                        if (n6 != 0) {
                            if (n6 >= 0) {
                                iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
                            }
                            n6 = 0x402 & 0xFFFFE064;
                            this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(IlIllllIllllllllIIIlIIlII);
                            lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x1617 & 0xFFFFC907, lllIIIlllIlIlllIlIIllIlIl.IlIllIIIIlIllllIlIIlIIlll);
                        }
                        d6 = ((double)(this.IIlIIIIIIlllllllllIIIIIII + j * j * (0xC71 & 0xFFFF8F39) + j * (0x2BA4AFB & 0x12F2EEBB) + i * i * (0x400663B7 & 0x253663DF) + i * (0xFFFFB5C7 & 0x3DC1) & (0x461F & 0xFFFF801F)) + (double)f) / (28.835164835164832 * 1.1097560975609757) * (0.30303030303030304 * 9.9 + this.lIlIlIIIIllllIIlllllllIlI.nextDouble());
                        d5 = (double)((float)j + 0.42613637f * 1.1733333f) - lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll;
                        d4 = (double)((float)i + 0.7894737f * 0.6333333f) - lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII;
                        float f5 = llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(d5 * d5 + d4 * d4) / (float)n5;
                        float f6 = ((1.0f - f5 * f5) * (0.37777779f * 1.3235294f) + 0.034722224f * 14.4f) * f2;
                        illIIIllIlIIlIllIIIllllIl.llIIlIlIllIlIIIllIllllIlI(j, n11, i);
                        int n12 = illIlIlIllllIllIIIllIIlII.lllIllIllIlIIIlllIIllllII((llllIllIllllIlIlllllIlIlI)illIIIllIlIIlIllIIIllllIl, 0xFFFF9A20 & 0x6582);
                        int n13 = n12 >> (0xFFFF9271 & 0x12) & (0x84FFFF & 0x9413FFFF);
                        int n14 = n12 & (0x2200FFFF & 0x88CFFFF);
                        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j - d7 + 1.263157894736842 * 0.39583333333333337, (double)n9, (double)i - d8 + 0.2 * 2.5).lllIllIllIlIIIlllIIllllII(0.0, (double)n9 * (0.0026041666666666665 * 96.0) + d6).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f6).lllIllIllIlIIIlllIIllllII(n13, n14).IIIlIlIIlllllIIIlllIllIll();
                        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j + d7 + 0.3941176470588235 * 1.2686567164179106, (double)n9, (double)i + d8 + 2.5 * 0.2).lllIllIllIlIIIlllIIllllII(1.0, (double)n9 * (1.3636363636363635 * 0.18333333333333335) + d6).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f6).lllIllIllIlIIIlllIIllllII(n13, n14).IIIlIlIIlllllIIIlllIllIll();
                        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j + d7 + 0.5555555555555556 * 0.9, (double)n10, (double)i + d8 + 0.18125 * 2.7586206896551726).lllIllIllIlIIIlllIIllllII(1.0, (double)n10 * (5.3125 * 0.047058823529411764) + d6).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f6).lllIllIllIlIIIlllIIllllII(n13, n14).IIIlIlIIlllllIIIlllIllIll();
                        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j - d7 + 0.7551020408163265 * 0.6621621621621622, (double)n10, (double)i - d8 + 1.1153846153846154 * 0.4482758620689655).lllIllIllIlIIIlllIIllllII(0.0, (double)n10 * (2.5 * 0.1) + d6).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f6).lllIllIllIlIIIlllIIllllII(n13, n14).IIIlIlIIlllllIIIlllIllIll();
                        continue;
                    }
                    if (n6 != (0x2501 & 0xFFFFD041)) {
                        if (n6 >= 0) {
                            iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
                        }
                        n6 = 0x81 & 0x5A4B;
                        this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(IllIIllIlIlIlIlllIlIlIllI);
                        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x5847 & 0x407, lllIIIlllIlIlllIlIIllIlIl.IlIllIIIIlIllllIlIIlIIlll);
                    }
                    d6 = ((float)(this.IIlIIIIIIlllllllllIIIIIII & (0x41FF & 0x33FF)) + f) / (5.625f * 91.022224f);
                    d5 = this.lIlIlIIIIllllIIlllllllIlI.nextDouble() + (double)f3 * (0.6140350877192983 * 0.016285714285714285) * (double)((float)this.lIlIlIIIIllllIIlllllllIlI.nextGaussian());
                    d4 = this.lIlIlIIIIllllIIlllllllIlI.nextDouble() + (double)(f3 * (float)this.lIlIlIIIIllllIIlllllllIlI.nextGaussian()) * (0.06451612903225806 * 0.0155);
                    double d9 = (double)((float)j + 9.5f * 0.05263158f) - lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll;
                    double d10 = (double)((float)i + 0.8888889f * 0.5625f) - lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII;
                    float f7 = llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(d9 * d9 + d10 * d10) / (float)n5;
                    float f8 = ((1.0f - f7 * f7) * (0.8842106f * 0.3392857f) + 1.6585366f * 0.3014706f) * f2;
                    illIIIllIlIIlIllIIIllllIl.llIIlIlIllIlIIIllIllllIlI(j, n11, i);
                    int n15 = (illIlIlIllllIllIIIllIIlII.lllIllIllIlIIIlllIIllllII((llllIllIllllIlIlllllIlIlI)illIIIllIlIIlIllIIIllllIl, 0xCA7 & 0xFFFFC050) * (0xFFFF8143 & 0x20A3) + (0x82F288F4 & 0x64F821F9)) / (0x7004 & 0x80D);
                    int n16 = n15 >> (0xFFFF8910 & 0x30) & (0x8080FFFF & 0x2C02FFFF);
                    int n17 = n15 & (0xC168FFFF & 0x3000FFFF);
                    lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j - d7 + 1.0344827586206897 * 0.4833333333333333, (double)n9, (double)i - d8 + 0.8360655737704918 * 0.5980392156862745).lllIllIllIlIIIlllIIllllII(0.0 + d5, (double)n9 * (38.0 * 0.006578947368421052) + d6 + d4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f8).lllIllIllIlIIIlllIIllllII(n16, n17).IIIlIlIIlllllIIIlllIllIll();
                    lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j + d7 + 0.9333333333333333 * 0.5357142857142857, (double)n9, (double)i + d8 + 0.717391304347826 * 0.696969696969697).lllIllIllIlIIIlllIIllllII(1.0 + d5, (double)n9 * (0.11231884057971016 * 2.225806451612903) + d6 + d4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f8).lllIllIllIlIIIlllIIllllII(n16, n17).IIIlIlIIlllllIIIlllIllIll();
                    lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j + d7 + 0.12365591397849461 * 4.043478260869565, (double)n10, (double)i + d8 + 1.25 * 0.4).lllIllIllIlIIIlllIIllllII(1.0 + d5, (double)n10 * (1.353846153846154 * 0.1846590909090909) + d6 + d4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f8).lllIllIllIlIIIlllIIllllII(n16, n17).IIIlIlIIlllllIIIlllIllIll();
                    lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)j - d7 + 0.8717948717948718 * 0.5735294117647058, (double)n10, (double)i - d8 + 1.4423076923076923 * 0.3466666666666667).lllIllIllIlIIIlllIIllllII(0.0 + d5, (double)n10 * (1.853658536585366 * 0.13486842105263158) + d6 + d4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f8).lllIllIllIlIIIlllIIllllII(n16, n17).IIIlIlIIlllllIIIlllIllIll();
                }
            }
            if (n6 >= 0) {
                iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
            }
            lIIIllIlIIIlIllIlIIllllIl2.IllllIllIIIlllIIllllllIII(0.0, 0.0, 0.0);
            lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
            lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x4714 & 0xFFFF9286, 0.71590906f * 0.13968255f);
            this.lIllllIIllIllllllIllIIIll();
        }
    }

    public void llIllllllIllllllllIllIIll() {
        if (!this.lIIllllIIlIIIllIlIlllIlII) {
            lIIlIlIIllIIIlIIlIlIIIlII.IllllIllIIIlllIIllllllIII();
            if (IIIIllIlIIIllIIIIIIlIlIll.IIIllIlIIlIIIllllIlllIlIl() == (0x63 & 0x5DC) && IIIIllIlIIIllIIIIIIlIlIll.IIllllIllIIIllIllIIIIIIlI() == (0x921 & 0xA0)) {
                IIIIllIlIIIllIIIIIIlIlIll.lllIllIllIlIIIlllIIllllII((0x845 & 0xFFFFC2B3) != 0);
            }
            this.lIIllllIIlIIIllIlIlllIlII = true;
        }
        IIIIllIlIIIllIIIIIIlIlIll.IlIIllIlIlIllIIIlIIlIlIIl();
        IllIlIlIllllIllIIIllIIlII illIlIlIllllIllIIIllIIlII = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII;
        if (illIlIlIllllIllIIIllIIlII != null) {
            Object object;
            if (IIIIllIlIIIllIIIIIIlIlIll.IIllIlIIlIlIIIllIIlIlIIlI() != null) {
                object = Strings13.strings[0x4AE6 & 0xFFFF82D6].replace(Strings13.strings[0xED6 & 0x43C7], Strings13.strings[0x2FF & 0x6C7]).replace(Strings1.strings[0x627F & 0x1AFF], Strings13.strings[0x2ECA & 0xFFFF92C8]);
                String string = (String)object + Strings0.strings[0xFFFFC0D9 & 0x17DD] + IIIIllIlIIIllIIIIIIlIlIll.IIllIlIIlIlIIIllIIlIlIIlI();
                Object[] objectArray = new Object[0x20D & 0xD01];
                objectArray[0x2D19 & 6] = string;
                IIIllIlIIlIIIIIlIlIIlllII iIIllIlIIlIIIIIlIlIIlllII = new IIIllIlIIlIIIIIlIlIIlllII(IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings13.strings[0xECD & 0x52C9], objectArray));
                this.llIIIIIIlllIlIIlIlIIllIII.IIlIIIIIIlllllllllIIIIIII.IlIllIIIIlIllllIlIIlIIlll().lllIllIllIlIIIlllIIllllII(iIIllIlIIlIIIIIlIlIIlllII);
                IIIIllIlIIIllIIIIIIlIlIll.llIIIllIIlIIIlIllIllIIlII(null);
            }
            if (IIIIllIlIIIllIIIIIIlIlIll.lIIIllIlIIIIllIIllIIlIlll()) {
                IIIIllIlIIIllIIIIIIlIlIll.lllIllIllIlIIIlllIIllllII((0x41A0 & 0x600) != 0);
                object = new IIIllIlIIlIIIIIlIlIIlllII(IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings13.strings[0xFFFF86CA & 0x33CF], new Object[0xFFFF8221 & 0x4082]));
                this.llIIIIIIlllIlIIlIlIIllIII.IIlIIIIIIlllllllllIIIIIII.IlIllIIIIlIllllIlIIlIIlll().lllIllIllIlIIIlllIIllllII((lllllllIlIIllIIIlIlllllIl)object);
            }
        }
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII instanceof llIIIIlIlIIllIlIIIIIlIlll) {
            this.lllIllIllIlIIIlllIIllllII((llIIIIlIlIIllIlIIIIIlIlll)this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII);
        }
        if (this.IIIIlllllIIIllllllIlIllll != illIlIlIllllIllIIIllIIlII) {
            IlIlllIllllIIlIIlIlIIllIl.lllIllIllIlIIIlllIIllllII(this.IIIIlllllIIIllllllIlIllll, (llIllllIllIIIlIIIllIlIlll)illIlIlIllllIllIIIllIIlII);
            IIIIllIlIIIllIIIIIIlIlIll.IllIIllIlIlIlIlllIlIlIllI();
            this.IIIllllllIllIIIIIIIllIIll = 0x80110E0L & 0x40622415L;
            this.IIlllIIIIIlllIllIIIllIlIl = 0x384 & 0xFFFFBC12;
            this.IIIIlllllIIIllllllIlIllll = illIlIlIllllIllIIIllIIlII;
        }
        if (!this.lllIllIllIlIIIlllIIllllII(IlllIllIIIlIIIlllIllIIlII.lIllllIlIlIIIIIlIIIlllllI)) {
            IlllIllIIIlIIIlllIllIIlII.lIllllIlIlIIIIIlIIIlllllI = 0x220 & 0x5949;
        }
    }

    public void llIIIllIIlIIIlIllIllIIlII(float f) {
        if (this.IIlIlIllllllIllllIIIIIllI) {
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII(Strings13.strings[0xFFFF9AB3 & 0x42B5]);
            IllIlIlIllllIllIIIllIIlII illIlIlIllllIllIIIllIIlII = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII;
            if (illIlIlIllllIllIIIllIIlII != null) {
                if (IIIIllIlIIIllIIIIIIlIlIll.lllIIlllllIIllIlIIIlIllll() && lllIIllIIIlIIlIIIIIlIlIII.lllIllIllIlIIIlllIIllllII(illIlIlIllllIllIIIllIIlII, this.lIIIlIllIlIlIlIlIIlIIIIIl, this.IlIlIIlIlIllIIlIlIIllIIIl, this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IIlIIIIIIlllllllllIIIIIII), f)) {
                    this.lllIllIIlllIllIlllIIlllII.llIIlIlIllIlIIIllIllllIlI();
                    this.IIlIlIllllllIllllIIIIIllI = false;
                    this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.llIIlIlIllIlIIIllIllllIlI();
                    return;
                }
                float f2 = illIlIlIllllIllIIIllIIlII.llIIlIlIllIlIIIllIllllIlI(1.0f);
                float f3 = f2 * (0.684f * 1.3888888f) + 0.19f * 0.2631579f;
                for (int i = 0x8C0 & 0xFFFF8532; i < (0xFFFFEF00 & 0x1140); ++i) {
                    float f4;
                    float f5;
                    float f6 = illIlIlIllllIllIIIllIIlII.llIllllllIllllllllIllIIll.llIllllllIllllllllIllIIll()[i / (0xFFFFDC18 & 0x390)] * f3;
                    float f7 = illIlIlIllllIllIIIllIIlII.llIllllllIllllllllIllIIll.llIllllllIllllllllIllIIll()[i % (0x3011 & 0x318)] * (this.lIIIlIllIlIlIlIlIIlIIIIIl * (0.09662921f * 1.0348837f) + 2.1101694f * 0.7108434f);
                    if (illIlIlIllllIllIIIllIIlII.IlIIlIIlIllIIIllllIIIlIIl() > 0) {
                        f6 = illIlIlIllllIllIIIllIIlII.llIllllllIllllllllIllIIll.llIllllllIllllllllIllIIll()[i / (0x796 & 0x810)];
                    }
                    float f8 = f6 * (f2 * (1.5744681f * 0.4128378f) + 0.3108108f * 1.126087f);
                    float f9 = f6 * (f2 * (4.25f * 0.15294117f) + 0.46492538f * 0.752809f);
                    float f10 = f7 * ((f7 * (7.2f * 0.083333336f) + 0.46233767f * 0.8651685f) * (0.7f * 0.8571429f) + 0.16363637f * 2.4444444f);
                    float f11 = f7 * (f7 * f7 * (1.75f * 0.34285715f) + 0.39772728f * 1.0057143f);
                    float f12 = f8 + f7;
                    float f13 = f9 + f10;
                    float f14 = f6 + f11;
                    f12 = f12 * (0.9302326f * 1.032f) + 0.017878788f * 1.6779661f;
                    f13 = f13 * (0.6055384f * 1.5853659f) + 0.4651163f * 0.0645f;
                    f14 = f14 * (2.612903f * 0.3674074f) + 1.6451613f * 0.018235294f;
                    if (this.IIIllIIIlllIIIIlIlIIIIlIl > 0.0f) {
                        f5 = this.IIllIIIIIlIIIlIllIlIIllII + (this.IIIllIIIlllIIIIlIlIIIIlIl - this.IIllIIIIIlIIIlIllIlIIllII) * f;
                        f12 = f12 * (1.0f - f5) + f12 * (0.65f * 1.0769231f) * f5;
                        f13 = f13 * (1.0f - f5) + f13 * (1.1846154f * 0.5064935f) * f5;
                        f14 = f14 * (1.0f - f5) + f14 * (0.36f * 1.6666666f) * f5;
                    }
                    if (illIlIlIllllIllIIIllIIlII.llIllllllIllllllllIllIIll.IIIlIllIlIIlIlIIIlIlIlIll() == (0x4181 & 0xFFFF9805)) {
                        f12 = 0.67391306f * 0.3264516f + f7 * (1.86f * 0.4032258f);
                        f13 = 1.3235294f * 0.21155557f + f10 * (1.5714285f * 0.47727275f);
                        f14 = 0.47959185f * 0.5212766f + f11 * (1.7272726f * 0.43421054f);
                    }
                    if (this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IIlIIIIIIlllllllllIIIIIII)) {
                        f5 = this.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll, f);
                        f4 = 1.0f / f12;
                        if (f4 > 1.0f / f13) {
                            f4 = 1.0f / f13;
                        }
                        if (f4 > 1.0f / f14) {
                            f4 = 1.0f / f14;
                        }
                        f12 = f12 * (1.0f - f5) + f12 * f4 * f5;
                        f13 = f13 * (1.0f - f5) + f13 * f4 * f5;
                        f14 = f14 * (1.0f - f5) + f14 * f4 * f5;
                    }
                    if (f12 > 1.0f) {
                        f12 = 1.0f;
                    }
                    if (f13 > 1.0f) {
                        f13 = 1.0f;
                    }
                    if (f14 > 1.0f) {
                        f14 = 1.0f;
                    }
                    f5 = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIllllllIIIIlIIIlIIllIl;
                    f4 = 1.0f - f12;
                    float f15 = 1.0f - f13;
                    float f16 = 1.0f - f14;
                    f4 = 1.0f - f4 * f4 * f4 * f4;
                    f15 = 1.0f - f15 * f15 * f15 * f15;
                    f16 = 1.0f - f16 * f16 * f16 * f16;
                    f12 = f12 * (1.0f - f5) + f4 * f5;
                    f13 = f13 * (1.0f - f5) + f15 * f5;
                    f14 = f14 * (1.0f - f5) + f16 * f5;
                    f12 = f12 * (0.5967742f * 1.6086485f) + 0.6060606f * 0.049499996f;
                    f13 = f13 * (4.446316f * 0.2159091f) + 0.07457143f * 0.40229884f;
                    f14 = f14 * (0.6339623f * 1.5142857f) + 0.016022727f * 1.8723404f;
                    if (f12 > 1.0f) {
                        f12 = 1.0f;
                    }
                    if (f13 > 1.0f) {
                        f13 = 1.0f;
                    }
                    if (f14 > 1.0f) {
                        f14 = 1.0f;
                    }
                    if (f12 < 0.0f) {
                        f12 = 0.0f;
                    }
                    if (f13 < 0.0f) {
                        f13 = 0.0f;
                    }
                    if (f14 < 0.0f) {
                        f14 = 0.0f;
                    }
                    int n = 0xFFFF90FF & 0x4BFF;
                    int n2 = (int)(f12 * (1.0465117f * 243.66666f));
                    int n3 = (int)(f13 * (395.25f * 0.6451613f));
                    int n4 = (int)(f14 * (1.2222222f * 208.63637f));
                    this.IlIlIIlIlIllIIlIlIIllIIIl[i] = n << (0x2318 & 0x1A) | n2 << (0x47B8 & 0x2813) | n3 << (0x314A & 9) | n4;
                }
                this.lllIllIIlllIllIlllIIlllII.llIIlIlIllIlIIIllIllllIlI();
                this.IIlIlIllllllIllllIIIIIllI = false;
                this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.llIIlIlIllIlIIIllIllllIlI();
            }
        }
    }

    public lIIlIlIllIIIIlIlIlllIIIII(lIIIIIIllIIIIIIIlllllllII lIIIIIIllIIIIIIIlllllllII2, IlIIIIIlIIIIIIIlllIlIllII ilIIIIIlIIIIIIIlllIlIllII) {
        this.lIlIIllIlIlIIlIlllIIllIII = 0.29761904f * 13.440001f;
        this.llllIllIllIlIlIlIIIlIllll = 0.51111114f * 7.8260865f;
        this.lIlllIIIlIIlIIIlIlIIIIlII = true;
        this.IlIlIIIlIlIIlIIIlllIllIII = true;
        this.IllllIIlIllIIlIllIlIlIlIl = lIIIIIIllIIIIIIIlllllllII.lIlllIIIlIIlIIIlIlIIIIlII();
        this.llIllIlllIllIlIIIIlIIlIII = new float[0x400 & 0x4522];
        this.lIIllllllIIIIllllllIIIlll = new float[0xFFFFA40A & 0x1F01];
        this.lllllIlIllIlIlllIIIlIIlIl = IIIIllIllIllIlIlIlIlIIIII.IIIlIlIIlllllIIIlllIllIll(0x992 & 0x30);
        this.IIIIIlIIIllIllllIIlllIIII = 0xFFFFD243 & 0x88;
        this.IIlIlIlllllIllllIllllIllI = false;
        this.lIIllllIIlIIIllIlIlllIlII = false;
        this.lIllllIlllllllllIllllIIll = false;
        this.lIIIlIIIlIllIlllIIIIIlIlI = false;
        this.polyBlurFarPlaneDistance = 8.0f * 16.0f;
        this.IIIllllllIllIIIIIIIllIIll = 0xCF8B718D944B4045L & 0x2901598L;
        this.IIlllIIIIIlllIllIIIllIlIl = 0x644 & 0x41B2;
        this.IlIllIIlllllIllIIIlIIIlll = 0x190 & 0xFFFFD002;
        this.IllIlllIIIlIIllllllIllIII = 0x2620 & 0xFFFFC048;
        this.IllIlllIIIlllllIllIIlIlIl = 0xBB342D4671224400L & 0x44CBD2B98E188806L;
        this.lIlIIIllllIlllIlIllllIlll = new IIlIIllIIIIIlIllIIIIlllIl[0x167A & 0xFFFFA10E];
        this.IIIIlIllIlIIllIllIlIIlllI = lIllllIIllIllllllIllIIIll;
        this.IIlIIlIIlIlIllIlIllIlIIIl = false;
        this.lIIllIIlIIIllIlIIllIIlIll = 0x1244 & 0x592;
        this.llIIIIIIlllIlIIlIlIIllIII = lIIIIIIllIIIIIIIlllllllII2;
        this.IIlllllllllIlIllIlIlIIllI = ilIIIIIlIIIIIIIlllIlIllII;
        this.IllllIllIIIlllIIllllllIII = lIIIIIIllIIIIIIIlllllllII2.llllIllIllIIIlllIIlllIIll();
        this.IIIlIllIlIIlIlIIIlIlIlIll = new llIlllIIIllllIIlllIllIIIl(lIIIIIIllIIIIIIIlllllllII2.lIIIlIIIIIIIlIIIllIIIlIII());
        this.lllIllIIlllIllIlllIIlllII = new lIllllIIIIIIlllIIllIIlIlI(0xFFFF8113 & 0x5010, 0x3E10 & 0x119);
        this.lIIIlIIIIIIIlIIIllIIIlIII = lIIIIIIllIIIIIIIlllllllII2.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(Strings13.strings[0x1AAB & 0x6AF], this.lllIllIIlllIllIlllIIlllII);
        this.IlIlIIlIlIllIIlIlIIllIIIl = this.lllIllIIlllIllIlllIIlllII.llIIIllIIlIIIlIllIllIIlII();
        this.lIlIlIllllIIlllIIIllllIlI = null;
        for (int i = 0x2406 & 0xFFFFC038; i < (0x2A0 & 0x17D); ++i) {
            for (int j = 0x713C & 0x280; j < (0xFFFFC568 & 0x28A2); ++j) {
                float f = j - (0x4491 & 0x3230);
                float f2 = i - (0x3210 & 0xFFFFC4D6);
                float f3 = llIllIIlIllllllIlllIlIlIl.IllllIllIIIlllIIllllllIII(f * f + f2 * f2);
                this.llIllIlllIllIlIIIIlIIlIII[i << (0x6427 & 0xFFFF8085) | j] = -f2 / f3;
                this.lIIllllllIIIIllllllIIIlll[i << (0xFFFF9C45 & 0x29D) | j] = f / f3;
            }
        }
    }

    public void lllIllIllIlIIIlllIIllllII(float f, long l) {
        int n;
        IIIIllIlIIIllIIIIIIlIlIll.lllllIIIlIIllllllllIlllIl = f;
        this.llIllllllIllllllllIllIIll();
        boolean bl = Display.isActive();
        if (!(bl || !this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIllIllllIllIlIIIllIIllll || this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIIIIllIlIIIIlIlllIllllI && Mouse.isButtonDown(0x401 & 0x83B))) {
            if (lIIIIIIllIIIIIIIlllllllII.lIlllIIIlIIlIIIlIlIIIIlII() - this.IllllIIlIllIIlIllIlIlIlIl > (0x420041F6L & 0x401F4L)) {
                this.llIIIIIIlllIlIIlIlIIllIII.lIlIlIIIIllllIIlllllllIlI();
            }
        } else {
            this.IllllIIlIllIIlIllIlIlIlIl = lIIIIIIllIIIIIIIlllllllII.lIlllIIIlIIlIIIlIlIIIIlII();
        }
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII(Strings1.strings[0x9C2 & 0x75D8]);
        if (bl && lIIIIIIllIIIIIIIlllllllII.lllIllIllIlIIIlllIIllllII && this.llIIIIIIlllIlIIlIlIIllIII.lIlIlIIIIlIlIlllIIIllllIl && !Mouse.isInsideWindow()) {
            Mouse.setGrabbed((0x28 & 0xFFFFD290) != 0);
            Mouse.setCursorPosition(Display.getWidth() / (0x202 & 0x22), Display.getHeight() / (0x201E & 0x41C3));
            Mouse.setGrabbed((0x1421 & 0x281) != 0);
        }
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIlIIIIlIlIlllIIIllllIl && bl) {
            this.llIIIIIIlllIlIIlIlIIllIII.llllIllIllIlIlIlIIIlIllll.IllllIllIIIlllIIllllllIII();
            float f2 = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII * (0.34736842f * 1.7272727f) + 0.22857143f * 0.875f;
            float f3 = f2 * f2 * f2 * (4.214286f * 1.898305f);
            float f4 = (float)this.llIIIIIIlllIlIIlIlIIllIII.llllIllIllIlIlIlIIIlIllll.lllIllIllIlIIIlllIIllllII * f3;
            float f5 = (float)this.llIIIIIIlllIlIIlIlIIllIII.llllIllIllIlIlIlIIIlIllll.llIIlIlIllIlIIIllIllllIlI * f3;
            n = 9 & 0x2875;
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.llIIlIlIllIlIIIllIllllIlI) {
                n = 0xFFFFFFFF & 0xFFFFFFFF;
            }
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIlIlIIllIIIIlIlIlIl) {
                this.IIIIlllIIIlIlIlIIIIIlllIl += f4;
                this.lIlIlIIIIlIlIlllIIIllllIl += f5;
                float f6 = f - this.IIlIIIllIIIlIlllIIIIllllI;
                this.IIlIIIllIIIlIlllIIIIllllI = f;
                f4 = this.lIllIllllIllIlIIIllIIllll * f6;
                f5 = this.IIIIIIllIlIIIIlIlllIllllI * f6;
                this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.IllllIllIIIlllIIllllllIII(f4, f5 * (float)n);
            } else {
                this.IIIIlllIIIlIlIlIIIIIlllIl = 0.0f;
                this.lIlIlIIIIlIlIlllIIIllllIl = 0.0f;
                this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.IllllIllIIIlllIIllllllIII(f4, f5 * (float)n);
            }
        }
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.llIIlIlIllIlIIIllIllllIlI();
        if (!this.llIIIIIIlllIlIIlIlIIllIII.lllllIIIlIIllllllllIlllIl) {
            lllIllIllIlIIIlllIIllllII = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll;
            IIIllllIIIllIIIlIllIIllIl iIIllllIIIllIIIlIllIIllIl = new IIIllllIIIllIIIlIllIIllIl(this.llIIIIIIlllIlIIlIlIIllIII);
            int n2 = iIIllllIIIllIIIlIllIIllIl.lllIllIllIlIIIlllIIllllII();
            int n3 = iIIllllIIIllIIIlIllIIllIl.llIIlIlIllIlIIIllIllllIlI();
            int n4 = Mouse.getX() * n2 / this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll;
            n = n3 - Mouse.getY() * n3 / this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll - (0xFFFF8143 & 0x2889);
            int n5 = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIllllIIllIllllllIllIIIll;
            if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII != null) {
                this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII(Strings1.strings[0x41E5 & 0x23CC]);
                int n6 = Math.min(lIIIIIIllIIIIIIIlllllllII.IIIIlIllIlIIllIllIlIIlllI(), n5);
                n6 = Math.max(n6, 0x2BD & 0x647C);
                long l2 = System.nanoTime() - l;
                long l3 = Math.max((long)((0x3F9BDE86 & 0x3BDAEB41) / n6 / (0xFFFF8005 & 0x7B4)) - l2, 0x5230A016L & 0x464C40L);
                this.llIIlIlIllIlIIIllIllllIlI(f, System.nanoTime() + l3);
                if (IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI) {
                    this.llIIIIIIlllIlIIlIlIIllIII.lIllllIIllIllllllIllIIIll.llIIlIlIllIlIIIllIllllIlI();
                    this.syncPolyBlurPhosphorState();
                    if (this.polyBlurPhosphorShader != null) {
                        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(5890);
                        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
                        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
                        this.polyBlurPhosphorShader.lllIllIllIlIIIlllIIllllII(f);
                        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
                    }
                    if (this.lIlIlIllllIIlllIIIllllIlI != null && this.IIlIIlIIlIlIllIlIllIlIIIl) {
                        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x5FA6 & 0x1742);
                        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
                        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
                        this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII(f);
                        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
                    }
                    this.llIIIIIIlllIlIIlIlIIllIII.llIIlIlIllIlIIIllIllllIlI().lllIllIllIlIIIlllIIllllII((0x4251 & 0x2123) != 0);
                }
                this.lIllIllllllllIlllIIllIIII = System.nanoTime();
                this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings1.strings[0x21BA & 0xFFFF95FB]);
                if (!this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIIIIlIIIlllIllIIllIllll || this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII != null) {
                    lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0xFFFFC605 & 0x3304, 0.10235294f * 0.9770115f);
                    this.llIIIIIIlllIlIIlIlIIllIII.IIlIIIIIIlllllllllIIIIIII.lllIllIllIlIIIlllIIllllII(f);
                    if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIIIlllIIllllIlllIlllIIl && !this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIIllIIIlIlIllIlIIlI) {
                        IIIIllIlIIIllIIIIIIlIlIll.IlIlllIIllIIllllllllIIlIl();
                    }
                    if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIIllIIIlIlIllIlIIlI) {
                        IllIIlllIlllIIlIlIIIIIlll.lllIllIllIlIIIlllIIllllII(iIIllllIIIllIIIlIllIIllIl);
                    }
                }
                this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.llIIlIlIllIlIIIllIllllIlI();
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0x7435 & 0x240, 0x22E1 & 0x10A, this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll, this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll);
                lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1759 & 0x37A1);
                lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
                lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x174B & 0x3734);
                lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
                this.lIIIlIIIlIllIlllIIIIIlIlI();
                this.lIllIllllllllIlllIIllIIII = System.nanoTime();
                IllllIllIIIIIIIIIIlIIIlIl.lllIllIllIlIIIlllIIllllII.IIIlIlIIlllllIIIlllIllIll = this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII();
            }
            if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII != null) {
                lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII(0xFFFF8300 & 0x3114);
                try {
                    this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII.lllIllIllIlIIIlllIIllllII(n4, n, f);
                }
                catch (Throwable throwable) {
                    lIllIlIIIlIlIlllIllIIlIII lIllIlIIIlIlIlllIllIIlIII2 = lIllIlIIIlIlIlllIllIIlIII.lllIllIllIlIIIlllIIllllII(throwable, Strings13.strings[0x2EB3 & 0x2BE]);
                    lIIIIIIIIlIIlIlllllllIlII lIIIIIIIIlIIlIlllllllIlII2 = lIllIlIIIlIlIlllIllIIlIII2.lllIllIllIlIIIlllIIllllII(Strings13.strings[0x52F7 & 0xFFFF82B3]);
                    lIIIIIIIIlIIlIlllllllIlII2.lllIllIllIlIIIlllIIllllII(Strings1.strings[0x51BE & 0x9FF], new IIlllIIIlllllIIllIllIllll(this));
                    lIIIIIIIIlIIlIlllllllIlII2.lllIllIllIlIIIlllIIllllII(Strings13.strings[0x22B4 & 0x2B6], new llIlllllIlIllllllllllllll(this, n4, n));
                    lIIIIIIIIlIIlIlllllllIlII2.lllIllIllIlIIIlllIIllllII(Strings13.strings[0xAF5 & 0x42B7], new lIIIIlllIlIIlllllllIlllll(this, iIIllllIIIllIIIlIllIIllIl));
                    throw new IIIlIIlllIllllllIlllllIII(lIllIlIIIlIlIlllIllIIlIII2);
                }
            }
        }
        this.IIIlIllIlIIlIlIIIlIlIlIll();
        this.lIlIlIIIIllllIIlllllllIlI();
        IllIIlllIlllIIlIlIIIIIlll.llIIlIlIllIlIIIllIllllIlI();
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIIllIlIlIllIIIlIIlIlIIl) {
            this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIlIllllIllIIlIIlIllII = true;
        }
    }

    public boolean lllIllIllIlIIIlllIIllllII() {
        return (IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI && this.lIlIlIllllIIlllIIIllllIlI != null ? 0x4C1 & 0x23 : 0xFFFF8286 & 0x49) != 0;
    }

    public llIlllIIIllllIIlllIllIIIl lllIlIlllIIlIlIIlIlllIIlI() {
        return this.IIIlIllIlIIlIlIIIlIlIlIll;
    }

    public void IlIllllIllllllllIIIlIIlII() {
        float f = 1.0f;
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() instanceof lIIIIlllIlIIllIIIllllIIII) {
            lIIIIlllIlIIllIIIllllIIII lIIIIlllIlIIllIIIllllIIII2 = (lIIIIlllIlIIllIIIllllIIII)this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
            f = lIIIIlllIlIIllIIIllllIIII2.lllIlIlllIIlIlIIlIlllIIlI();
        }
        this.llIllIlIlIIIIlIIIIllIllll = this.IIIIllIIIIIlIlIlllIIllIll;
        this.IIIIllIIIIIlIlIlllIIllIll += (f - this.IIIIllIIIIIlIlIlllIIllIll) * (0.53571427f * 0.93333334f);
        if (this.IIIIllIIIIIlIlIlllIIllIll > 1.1578947f * 1.2954545f) {
            this.IIIIllIIIIIlIlIlllIIllIll = 0.7285714f * 2.0588236f;
        }
        if (this.IIIIllIIIIIlIlIlllIIllIll < 0.2f * 0.5f) {
            this.IIIIllIIIIIlIlIlllIIllIll = 0.13666667f * 0.73170733f;
        }
    }

    public void lIIllIIlIIIllIlIIllIIlIll(float f) {
        float f2;
        float f3;
        Object object;
        float[] polyBlurFogColorArray;
        IlIIIIIlllllllIIIIlllIIll polyBlurSkyColorVec;
        float f4;
        Object object2;
        IllIlIlIllllIllIIIllIIlII illIlIlIllllIllIIIllIIlII = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII;
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        float f5 = 1.1785715f * 0.2121212f + 1.1445783f * 0.6552631f * (float)this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IllllIllIIIlllIIllllllIII / (9.014085f * 3.55f);
        f5 = 1.0f - (float)Math.pow(f5, 0.20652173913043478 * 1.2105263157894737);
        IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll = illIlIlIllllIllIIIllIIlII.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI(), f);
        ilIIIIIlllllllIIIIlllIIll = lllIIllIIIlIIlIIIIIlIlIII.lllIllIllIlIIIlllIIllllII(ilIIIIIlllllllIIIIlllIIll, (llIllllIllIIIlIIIllIlIlll)illIlIlIllllIllIIIllIIlII, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI(), f);
        float f6 = (float)ilIIIIIlllllllIIIIlllIIll.lllIllIllIlIIIlllIIllllII;
        float f7 = (float)ilIIIIIlllllllIIIIlllIIll.llIIlIlIllIlIIIllIllllIlI;
        float f8 = (float)ilIIIIIlllllllIIIIlllIIll.IllllIllIIIlllIIllllllIII;
        IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll2 = illIlIlIllllIllIIIllIIlII.llIIIllIIlIIIlIllIllIIlII(f);
        ilIIIIIlllllllIIIIlllIIll2 = lllIIllIIIlIIlIIIIIlIlIII.lllIllIllIlIIIlllIIllllII(ilIIIIIlllllllIIIIlllIIll2, illIlIlIllllIllIIIllIIlII, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI(), f);
        this.IlIllIIIIlIllllIlIIlIIlll = (float)ilIIIIIlllllllIIIIlllIIll2.lllIllIllIlIIIlllIIllllII;
        this.IIIlIlIIlllllIIIlllIllIll = (float)ilIIIIIlllllllIIIIlllIIll2.llIIlIlIllIlIIIllIllllIlI;
        this.llIIIllIIlIIIlIllIllIIlII = (float)ilIIIIIlllllllIIIIlllIIll2.IllllIllIIIlllIIllllllIII;
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IllllIllIIIlllIIllllllIII >= (0x206 & 0x106D)) {
            double d = -0.25 * 4.0;
            object2 = llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(illIlIlIllllIllIIIllIIlII.IlIllIIIIlIllllIlIIlIIlll(f)) > 0.0f ? new IlIIIIIlllllllIIIIlllIIll(d, 0.0, 0.0) : new IlIIIIIlllllllIIIIlllIIll(1.0, 0.0, 0.0);
            f4 = (float)lllIIIIIlllIIlIllIIlIIIlI2.IllllIllIIIlllIIllllllIII(f).llIIlIlIllIlIIIllIllllIlI((IlIIIIIlllllllIIIIlllIIll)object2);
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
            if (f4 > 0.0f && (polyBlurFogColorArray = illIlIlIllllIllIIIllIIlII.llIllllllIllllllllIllIIll.lllIllIllIlIIIlllIIllllII(illIlIlIllllIllIIIllIIlII.IllllIllIIIlllIIllllllIII(f), f)) != null) {
                this.IlIllIIIIlIllllIlIIlIIlll = this.IlIllIIIIlIllllIlIIlIIlll * (1.0f - (f4 *= polyBlurFogColorArray[3])) + polyBlurFogColorArray[0] * f4;
                this.IIIlIlIIlllllIIIlllIllIll = this.IIIlIlIIlllllIIIlllIllIll * (1.0f - f4) + polyBlurFogColorArray[1] * f4;
                this.llIIIllIIlIIIlIllIllIIlII = this.llIIIllIIlIIIlIllIllIIlII * (1.0f - f4) + polyBlurFogColorArray[2] * f4;
            }
        }
        this.IlIllIIIIlIllllIlIIlIIlll += (f6 - this.IlIllIIIIlIllllIlIIlIIlll) * f5;
        this.IIIlIlIIlllllIIIlllIllIll += (f7 - this.IIIlIlIIlllllIIIlllIllIll) * f5;
        this.llIIIllIIlIIIlIllIllIIlII += (f8 - this.llIIIllIIlIIIlIllIllIIlII) * f5;
        float f9 = illIlIlIllllIllIIIllIIlII.lllIlIlllIIlIlIIlIlllIIlI(f);
        if (f9 > 0.0f) {
            f3 = 1.0f - f9 * (2.357143f * 0.2121212f);
            float f10 = 1.0f - f9 * (0.72888887f * 0.5487805f);
            this.IlIllIIIIlIllllIlIIlIIlll *= f3;
            this.IIIlIlIIlllllIIIlllIllIll *= f3;
            this.llIIIllIIlIIIlIllIllIIlII *= f10;
        }
        if ((f3 = illIlIlIllllIllIIIllIIlII.lIIllIIlIIIllIlIIllIIlIll(f)) > 0.0f) {
            float f11 = 1.0f - f3 * (1.3018868f * 0.38405797f);
            this.IlIllIIIIlIllllIlIIlIIlll *= f11;
            this.IIIlIlIIlllllIIIlllIllIll *= f11;
            this.llIIIllIIlIIIlIllIllIIlII *= f11;
        }
        object2 = IIIlIlllIIlIIIlIllIIlIlll.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII, lllIIIIIlllIIlIllIIlIIIlI2, f);
        if (this.lllIlIllllIlIIllIIIlIlllI) {
            IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll3 = illIlIlIllllIllIIIllIIlII.IIIlIlIIlllllIIIlllIllIll(f);
            this.IlIllIIIIlIllllIlIIlIIlll = (float)ilIIIIIlllllllIIIIlllIIll3.lllIllIllIlIIIlllIIllllII;
            this.IIIlIlIIlllllIIIlllIllIll = (float)ilIIIIIlllllllIIIIlllIIll3.llIIlIlIllIlIIIllIllllIlI;
            this.llIIIllIIlIIIlIllIllIIlII = (float)ilIIIIIlllllllIIIIlllIIll3.IllllIllIIIlllIIllllllIII;
        } else if (((IllIIIIIIIlllIIIlIlIlIlll)object2).llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lIIllIIlIIIllIlIIllIIlIll) {
            f4 = (float)lIIIlllIlIIIlIlIllIllIlll.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2) * (0.145f * 1.3793104f);
            if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.lIlIlIIIIllllIIlllllllIlI)) {
                f4 = f4 * (2.34f * 0.12820514f) + 0.6779221f * 0.88505745f;
            }
            this.IlIllIIIIlIllllIlIIlIIlll = 0.75510204f * 0.026486486f + f4;
            this.IIIlIlIIlllllIIIlllIllIll = 0.069230765f * 0.2888889f + f4;
            this.llIIIllIIlIIIlIllIllIIlII = 0.16231886f * 1.2321428f + f4;
            polyBlurSkyColorVec = lllIIllIIIlIIlIIIIIlIlIII.lllIllIllIlIIIlllIIllllII((lllIIllllIlIlIlllIIIIIlll)this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI().llIllIlIlIIIIlIIIIllIllll, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI().IIIllIIIlllIIIIlIlIIIIlIl + 1.0, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI().IIllIIIIIlIIIlIllIlIIllII);
            if (polyBlurSkyColorVec != null) {
                this.IlIllIIIIlIllllIlIIlIIlll = (float)polyBlurSkyColorVec.lllIllIllIlIIIlllIIllllII;
                this.IIIlIlIIlllllIIIlllIllIll = (float)polyBlurSkyColorVec.llIIlIlIllIlIIIllIllllIlI;
                this.llIIIllIIlIIIlIllIllIIlII = (float)polyBlurSkyColorVec.IllllIllIIIlllIIllllllIII;
            }
        } else if (((IllIIIIIIIlllIIIlIlIlIlll)object2).llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lIIIlIIIlIllIlllIIIIIlIlI) {
            this.IlIllIIIIlIllllIlIIlIIlll = 1.1111112f * 0.54f;
            this.IIIlIlIIlllllIIIlllIllIll = 0.3375f * 0.2962963f;
            this.llIIIllIIlIIIlIllIllIIlII = 0.0f;
            IlIIIIIlllllllIIIIlllIIll ilIIIIIlllllllIIIIlllIIll4 = lllIIllIIIlIIlIIIIIlIlIII.llIIlIlIllIlIIIllIllllIlI(this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI().llIllIlIlIIIIlIIIIllIllll, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI().IIIllIIIlllIIIIlIlIIIIlIl + 1.0, this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI().IIllIIIIIlIIIlIllIlIIllII);
            if (ilIIIIIlllllllIIIIlllIIll4 != null) {
                this.IlIllIIIIlIllllIlIIlIIlll = (float)ilIIIIIlllllllIIIIlllIIll4.lllIllIllIlIIIlllIIllllII;
                this.IIIlIlIIlllllIIIlllIllIll = (float)ilIIIIIlllllllIIIIlllIIll4.llIIlIlIllIlIIIllIllllIlI;
                this.llIIIllIIlIIIlIllIllIIlII = (float)ilIIIIIlllllllIIIIlllIIll4.IllllIllIIIlllIIllllllIII;
            }
        }
        float f12 = this.llIllllIlIllIlIlIIllIlIII + (this.lIllIllIIllIlllIIIlllIIIl - this.llIllllIlIllIlIlIIllIlIII) * f;
        this.IlIllIIIIlIllllIlIIlIIlll *= f12;
        this.IIIlIlIIlllllIIIlllIllIll *= f12;
        this.llIIIllIIlIIIlIllIllIIlII *= f12;
        double d = illIlIlIllllIllIIIllIIlII.llIllllllIllllllllIllIIll.lllIlIlllIIlIlIIlIlllIIlI();
        double d2 = (lllIIIIIlllIIlIllIIlIIIlI2.IlllIllIlIIIIIlllIlIIIIll + (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl - lllIIIIIlllIIlIllIIlIIIlI2.IlllIllIlIIIIIlllIlIIIIll) * (double)f) * d;
        if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IIIlIllIlIIlIlIIIlIlIlIll)) {
            int n = ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).llIIlIlIllIlIIIllIllllIlI(lIIIlllIllIIIlIlIIllIIlIl.IIIlIllIlIIlIlIIIlIlIlIll).llIIlIlIllIlIIIllIllllIlI();
            d2 = n < (0x1A14 & 0xFFFFA014) ? (d2 *= (double)(1.0f - (float)n / (0.5f * 40.0f))) : 0.0;
        }
        if (d2 < 1.0) {
            if (d2 < 0.0) {
                d2 = 0.0;
            }
            d2 *= d2;
            this.IlIllIIIIlIllllIlIIlIIlll = (float)((double)this.IlIllIIIIlIllllIlIIlIIlll * d2);
            this.IIIlIlIIlllllIIIlllIllIll = (float)((double)this.IIIlIlIIlllllIIIlllIllIll * d2);
            this.llIIIllIIlIIIlIllIllIIlII = (float)((double)this.llIIIllIIlIIIlIllIllIIlII * d2);
        }
        if (this.IIIllIIIlllIIIIlIlIIIIlIl > 0.0f) {
            float f13 = this.IIllIIIIIlIIIlIllIlIIllII + (this.IIIllIIIlllIIIIlIlIIIIlIl - this.IIllIIIIIlIIIlIllIlIIllII) * f;
            this.IlIllIIIIlIllllIlIIlIIlll = this.IlIllIIIIlIllllIlIIlIIlll * (1.0f - f13) + this.IlIllIIIIlIllllIlIIlIIlll * (3.2631578f * 0.21451613f) * f13;
            this.IIIlIlIIlllllIIIlllIllIll = this.IIIlIlIIlllllIIIlllIllIll * (1.0f - f13) + this.IIIlIlIIlllllIIIlllIllIll * (0.06428572f * 9.333333f) * f13;
            this.llIIIllIIlIIIlIllIllIIlII = this.llIIIllIIlIIIlIllIllIIlII * (1.0f - f13) + this.llIIIllIIlIIIlIllIllIIlII * (0.028125001f * 21.333334f) * f13;
        }
        if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IIlIIIIIIlllllllllIIIIIII)) {
            float f14 = this.lllIllIllIlIIIlllIIllllII((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2, f);
            f2 = 1.0f / this.IlIllIIIIlIllllIlIIlIIlll;
            if (f2 > 1.0f / this.IIIlIlIIlllllIIIlllIllIll) {
                f2 = 1.0f / this.IIIlIlIIlllllIIIlllIllIll;
            }
            if (f2 > 1.0f / this.llIIIllIIlIIIlIllIllIIlII) {
                f2 = 1.0f / this.llIIIllIIlIIIlIllIllIIlII;
            }
            if (Float.isInfinite(f2)) {
                f2 = Math.nextAfter(f2, 0.0);
            }
            this.IlIllIIIIlIllllIlIIlIIlll = this.IlIllIIIIlIllllIlIIlIIlll * (1.0f - f14) + this.IlIllIIIIlIllllIlIIlIIlll * f2 * f14;
            this.IIIlIlIIlllllIIIlllIllIll = this.IIIlIlIIlllllIIIlllIllIll * (1.0f - f14) + this.IIIlIlIIlllllIIIlllIllIll * f2 * f14;
            this.llIIIllIIlIIIlIllIllIIlII = this.llIIIllIIlIIIlIllIllIIlII * (1.0f - f14) + this.llIIIllIIlIIIlIllIllIIlII * f2 * f14;
        }
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll) {
            float f15 = (this.IlIllIIIIlIllllIlIIlIIlll * (27.209303f * 1.1025641f) + this.IIIlIlIIlllllIIIlllIllIll * (0.6666667f * 88.5f) + this.llIIIllIIlIIIlIllIllIIlII * (0.55f * 20.0f)) / (106.25f * 0.9411765f);
            f2 = (this.IlIllIIIIlIllllIlIIlIIlll * (1.5853659f * 18.923077f) + this.IIIlIlIIlllllIIIlllIllIll * (0.19402985f * 360.76923f)) / (93.44263f * 1.0701754f);
            float f16 = (this.IlIllIIIIlIllllIlIIlIIlll * (1.7142857f * 17.5f) + this.llIIIllIIlIIIlIllIllIIlII * (0.6935484f * 100.93024f)) / (0.33333334f * 300.0f);
            this.IlIllIIIIlIllllIlIIlIIlll = f15;
            this.IIIlIlIIlllllIIIlllIllIll = f2;
            this.llIIIllIIlIIIlIllIllIIlII = f16;
        }
        IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(this.IlIllIIIIlIllllIlIIlIIlll, this.IIIlIlIIlllllIIIlllIllIll, this.llIIIllIIlIIIlIllIllIIlII, 0.0f);
    }

    public boolean llIIIIIIlllIlIIlIlIIllIII() {
        int n;
        if (!this.IlIlIIIlIlIIlIIIlllIllIII) {
            return (0x4408 & 0xA00) != 0;
        }
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        int n2 = n = lllIIIIIlllIIlIllIIlIIIlI2 instanceof IIllIIIllIIlIlllllIlllIII && !this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIIIIlIIIlllIllIIllIllll ? 0xA3 & 0x3311 : 0x3AA0 & 0xFFFF814F;
        if (n != 0 && !((IIllIIIllIIlIlllllIlllIII)lllIIIIIlllIIlIllIIlIIIlI2).IllllIlllllIllIllIIIllllI.IIIlIlIIlllllIIIlllIllIll) {
            lllIlIllIllIIlIlllIllllll lllIlIllIllIIlIlllIllllll2 = ((IIllIIIllIIlIlllllIlllIII)lllIIIIIlllIIlIllIIlIIIlI2).lllIIlIIllIlIIlIIllIIlllI();
            if (this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI != null && this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI.lllIllIllIlIIIlllIIllllII == lIlIllIIllIIIIlllIlIlllIl.llIIlIlIllIlIIIllIllllIlI) {
                llllIllIllllIlIlllllIlIlI llllIllIllllIlIlllllIlIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI.llIIlIlIllIlIIIllIllllIlI();
                lllIIIIIIllIlllIlIIlIlIll lllIIIIIIllIlllIlIIlIlIll2 = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.llIIlIlIllIlIIIllIllllIlI(llllIllIllllIlIlllllIlIlI2);
                IllIIIIIIIlllIIIlIlIlIlll illIIIIIIIlllIIIlIlIlIlll = lllIIIIIIllIlllIlIIlIlIll2.IllllIllIIIlllIIllllllIII();
                n = this.llIIIIIIlllIlIIlIlIIllIII.IllllIllIIIlllIIllllllIII.IllIIllIlIlIlIlllIlIlIllI() == lIllIlIIIIIIlIlIlIIlIllll.IIIlIlIIlllllIIIlllIllIll ? (lllIIIIIIllIlllIlIIlIlIll2.IllllIllIIIlllIIllllllIII().IIlllllllllIlIllIlIlIIllI() && this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(llllIllIllllIlIlllllIlIlI2) instanceof lIlIIIIlIIIIlIlllIlllIIIl ? 0x424B & 0xFFFFA181 : 0x30A & 0x3004) : (lllIlIllIllIIlIlllIllllll2 != null && (lllIlIllIllIIlIlllIllllll2.IllllIllIIIlllIIllllllIII(illIIIIIIIlllIIIlIlIlIlll) || lllIlIllIllIIlIlllIllllll2.IlIllIIIIlIllllIlIIlIIlll(illIIIIIIIlllIIIlIlIlIlll)) ? 0x201 & 0x5851 : 0x1C8 & 0xFFFFB414);
            }
        }
        return n != 0;
    }

    public void IllllIllIIIlllIIllllllIII(float f) {
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() instanceof IllIlIIlIIllIlllIlIlIIlIl) {
            float f2;
            IllIlIIlIIllIlllIlIlIIlIl illIlIIlIIllIlllIlIlIIlIl = (IllIlIIlIIllIlllIlIlIIlIl)this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
            float f3 = (float)illIlIIlIIllIlllIlIlIIlIl.lllIIllllIIlIIIlIIIIllIlI - f;
            if (illIlIIlIIllIlllIlIlIIlIl.lllIIIlIlllllIIIIlIllIIll() <= 0.0f) {
                f2 = (float)illIlIIlIIllIlllIlIlIIlIl.IIlIIIIIIIIlIIIIIlIlIllII + f;
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(28.453608f * 1.4057971f - 2.44f * 3278.6885f / (f2 + 129.16667f * 1.548387f), 0.0f, 0.0f, 1.0f);
            }
            if (f3 < 0.0f) {
                return;
            }
            f3 /= (float)illIlIIlIIllIlllIlIlIIlIl.lIIIIIIlIlIIllIIIIlIlIlIl;
            if (IlIIllIIllIIIllllIIllIIII.llIIlIlIllIlIIIllIllllIlI().lIIllIIlIIIllIlIIllIIlIll().lIIllIIlIIIllIlIIllIIlIll().lllllIIIlIIllllllllIlllIl().lllIllIllIlIIIlllIIllllII()) {
                f3 /= 0.3152174f * 3.6482756f;
            }
            f3 = llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(f3 * f3 * f3 * f3 * (2.8643932f * 1.0967742f));
            f2 = illIlIIlIIllIlllIlIlIIlIl.IIIIlIlIlIIlIllllIIlIIIII;
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-f2, 0.0f, 1.0f, 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-f3 * (47.6f * 0.29411766f), 0.0f, 0.0f, 1.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f2, 0.0f, 1.0f, 0.0f);
        }
    }

    public void IllIIllIlIlIlIlllIlIlIllI() {
        this.IlIIlIIlIllIIIllllIIIlIIl = (float)((double)this.IlIIlIIlIllIIIllllIIIlIIl + (Math.random() - Math.random()) * Math.random() * Math.random());
        this.IlIIlIIlIllIIIllllIIIlIIl = (float)((double)this.IlIIlIIlIllIIIllllIIIlIIl * (5.7 * 0.15789473684210525));
        this.lIIIlIllIlIlIlIlIIlIIIIIl += (this.IlIIlIIlIllIIIllllIIIlIIl - this.lIIIlIllIlIlIlIlIIlIIIIIl) * 1.0f;
        this.IIlIlIllllllIllllIIIIIllI = true;
    }

    public IIlIIllIIIIIlIllIIIIlllIl llIIIllIIlIIIlIllIllIIlII() {
        return this.lIlIlIllllIIlllIIIllllIlI;
    }

    public void lllIllIllIlIIIlllIIllllII(llIIIIlIlIIllIlIIIIIlIlll llIIIIlIlIIllIlIIIIIlIlll2) {
        try {
            String string = null;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int n = calendar.get(0x1345 & 0x402D);
            int n2 = calendar.get(0x1096 & 0x42A) + (0x10F1 & 0xFFFF8801);
            if (n == (0x5089 & 0x4A) && n2 == (0x40E6 & 0xC04)) {
                string = Strings13.strings[0xFFFF92DE & 0x2CC];
            }
            if (n == (0x516F & 0x48E) && n2 == (0x4009 & 0xFFFF9AAA)) {
                string = Strings13.strings[0xFFFF82DD & 0x1ECD];
            }
            if (string == null) {
                return;
            }
            Field[] fieldArray = llIIIIlIlIIllIlIIIIIlIlll.class.getDeclaredFields();
            for (int i = 0x300 & 0x1802; i < fieldArray.length; ++i) {
                if (fieldArray[i].getType() != String.class) continue;
                fieldArray[i].setAccessible((0x2003 & 0x685) != 0);
                fieldArray[i].set(llIIIIlIlIIllIlIIIIIlIlll2, string);
                break;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public FloatBuffer lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4) {
        if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
            IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(f, f2, f3);
        }
        this.lllllIlIllIlIlllIIIlIIlIl.clear();
        this.lllllIlIllIlIlllIIIlIIlIl.put(f).put(f2).put(f3).put(f4);
        this.lllllIlIllIlIlllIIIlIIlIl.flip();
        return this.lllllIlIllIlIlllIIIlIIlIl;
    }

    public void lllIllIllIlIIIlllIIllllII(int n, int n2) {
        if (IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI) {
            if (this.lIlIlIllllIIlllIIIllllIlI != null) {
                this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII(n, n2);
            }
            this.llIIIIIIlllIlIIlIlIIllIII.lIllllIIllIllllllIllIIIll.lllIllIllIlIIIlllIIllllII(n, n2);
            if (this.polyBlurPhosphorShader != null) {
                this.polyBlurPhosphorShader.lllIllIllIlIIIlllIIllllII(n, n2);
            }
        }
    }

    private boolean isPolyBlurModuleEnabled() {
        return lllIIIllIIllIIIlIlllIIIll.polyBlurInstance != null && lllIIIllIIllIIIlIlllIIIll.polyBlurInstance.lllIllIllIlIIIlllIIllllII();
    }

    private float getPolyBlurStrength() {
        if (lllIIIllIIllIIIlIlllIIIll.polyBlurInstance == null || lllIIIllIIllIIIlIlllIIIll.polyBlurInstance.IIIlIlIIlllllIIIlllIllIll == null) {
            return 1.0f;
        }
        return ((Number)lllIIIllIIllIIIlIlllIIIll.polyBlurInstance.IIIlIlIIlllllIIIlllIllIll.llIllllllIllllllllIllIIll()).floatValue();
    }

    private void syncPolyBlurPhosphorState() {
        boolean enabled = this.isPolyBlurModuleEnabled() && IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI && !IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI();
        if (!enabled) {
            if (this.polyBlurPhosphorShader != null) {
                this.polyBlurPhosphorShader.lllIllIllIlIIIlllIIllllII();
                this.polyBlurPhosphorShader = null;
            }
            return;
        }
        if (this.polyBlurPhosphorShader == null) {
            try {
                this.polyBlurPhosphorShader = new IIlIIllIIIIIlIllIIIIlllIl(this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII(), this.IIlllllllllIlIllIlIlIIllI, this.llIIIIIIlllIlIIlIlIIllIII.llIIlIlIllIlIIIllIllllIlI(), new lIIlIllIlIIllIIllIlIlllIl("shaders/post/phosphor_motion_blur.json"));
                this.polyBlurPhosphorShader.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll, this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll);
            }
            catch (Exception exception) {
                this.polyBlurPhosphorShader = null;
                return;
            }
        }
        this.applyPolyBlurIntensity();
    }

    private void applyPolyBlurIntensity() {
        if (this.polyBlurPhosphorShader == null) {
            return;
        }
        float strength = this.getPolyBlurStrength();
        float weight = Math.max(Math.min(1.0f - strength / 10.0f + 0.1f, 1.0f), 0.1f);
        for (Object shaderObj : this.polyBlurPhosphorShader.lllIllIllIlIIIlllIIllllII) {
            IlllIllIIIIIIIIllIlllIlll shader = (IlllIllIIIIIIIIllIlllIlll)shaderObj;
            IIIIIlIIIIIllIlIlIllIIIll uniform = shader.llIIlIlIllIlIIIllIllllIlI().llIIlIlIllIlIIIllIllllIlI("Weight");
            if (uniform != null) {
                uniform.lllIllIllIlIIIlllIIllllII(weight);
            }
        }
    }

    public void lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2) {
        if (IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI) {
            if (this.lIlIlIllllIIlllIIIllllIlI != null) {
                this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII();
            }
            this.lIlIlIllllIIlllIIIllllIlI = null;
            if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IlllIIIIlIIlIllIIIllIlIIl) {
                this.llIIlIlIllIlIIIllIllllIlI(new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x6EC & 0xFFFFABAF]));
            } else if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IIlllllIlIlIIIIIIIIlIllII) {
                this.llIIlIlIllIlIIIllIllllIlI(new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0x6FD & 0x22AD]));
            } else if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof lIlIIIIIlllIIIIlIIlIllllI) {
                this.llIIlIlIllIlIIIllIllllIlI(new lIIlIllIlIIllIIllIlIlllIl(Strings13.strings[0xFFFFA6BE & 0x2AE]));
            }
        }
    }

    public float lllIllIllIlIIIlllIIllllII(float f, boolean bl) {
        IllIIIIIIIlllIIIlIlIlIlll illIIIIIIIlllIIIlIlIlIlll;
        Object object;
        if (this.IIlIlIlllllIllllIllllIllI) {
            return 0.8769231f * 102.63158f;
        }
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        float f2 = 280.0f * 0.25f;
        int n = 0x1C0B & 0x2010;
        if (bl) {
            IIlllIlIIIIIIlIlIlllllIlI iIlllIlIIIIIIlIlIlllllIlI = IlIIllIIllIIIllllIIllIIII.llIIlIlIllIlIIIllIllllIlI().lIIllIIlIIIllIlIIllIIlIll().lIlIlIIIIllllIIlllllllIlI();
            if (iIlllIlIIIIIIlIlIlllllIlI.lllIllIllIlIIIlllIIllllII()) {
                object = IlIIllIIllIIIllllIIllIIII.llIIIllIIlIIIlIllIllIIlII();
                if (((lllIIIIIlllIIlIllIIlIIIlI)object).IllIllllIIIlIIlllllIlIlIl()) {
                    f2 = ((Integer)iIlllIlIIIIIIlIlIlllllIlI.IIlIIIIIIlllllllllIIIIIII().IIlllllllllIlIllIlIlIIllI()).intValue();
                    n = 0x3AF & 0x6401;
                }
                if (((IlllIlllIlIIlllIIIIllIllI)object).IllllIlllllIllIllIIIllllI.llIIlIlIllIlIIIllIllllIlI) {
                    f2 = ((Integer)iIlllIlIIIIIIlIlIlllllIlI.lllllIIIlIIllllllllIlllIl().IIlllllllllIlIllIlIlIIllI()).intValue();
                    n = 0x2005 & 0x4A9;
                }
                if (((IllIlIIlIIllIlllIlIlIIlIl)object).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IlIllIIIIlIllllIlIIlIIlll)) {
                    f2 = ((Integer)iIlllIlIIIIIIlIlIlllllIlI.IIIlIllIlIIlIlIIIlIlIlIll().IIlllllllllIlIllIlIlIIllI()).intValue();
                    n = 0xFFFFAB01 & 0x5091;
                }
                if (((IllIlIIlIIllIlllIlIlIIlIl)object).lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IllllIllIIIlllIIllllllIII)) {
                    f2 = ((Integer)iIlllIlIIIIIIlIlIlllllIlI.lllIIllllIIlIIIlIIIIllIlI().IIlllllllllIlIllIlIlIIllI()).intValue();
                    n = 0x20C7 & 0x639;
                }
                if (n == 0) {
                    f2 = ((Integer)iIlllIlIIIIIIlIlIlllllIlI.llIllllllIllllllllIllIIll().IIlllllllllIlIllIlIlIIllI()).intValue();
                }
            } else {
                f2 = IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lIlIIllIlIlIIlIlllIIllIII.IIlIIIIIIIIlIIIIIlIlIllII;
            }
            if (!(!IIIIllIlIIIllIIIIIIlIlIll.IllIIIIlIllIlIllIlIIlIIlI() || iIlllIlIIIIIIlIlIlllllIlI.lllIllIllIlIIIlllIIllllII() && ((Boolean)iIlllIlIIIIIIlIlIlllllIlI.lllIllIllIIIIllIIlIIlIlll().IIlllllllllIlIllIlIlIIllI()).booleanValue())) {
                f2 *= this.llIllIlIlIIIIlIIIIllIllll + (this.IIIIllIIIIIlIlIlllIIllIll - this.llIllIlIlIIIIlIIIIllIllll) * f;
            }
        }
        int n2 = 0xFFFFEDF5 & 0;
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIIIIlllIlIIlIlIIllIII == null) {
            object = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII;
            n2 = IllIIllIIlIlIlIllIlIIIlll.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIIlIIIIlllIlIllIlIIlIIl) ? 1 : 0;
        }
        if (n2 != 0) {
            if (!IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIllllIIlllllllIlI) {
                IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIllllIIlllllllIlI = true;
                IIIIllIlIIIllIIIIIIlIlIll.llIllllllIllllllllIllIIll = 1.0f;
                this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIlIlIIllIIIIlIlIlIl = true;
            }
            if (IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIllllIIlllllllIlI) {
                f2 /= 7.404256f * 0.54022986f * IIIIllIlIIIllIIIIIIlIlIll.llIllllllIllllllllIllIIll;
            }
        } else if (IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIllllIIlllllllIlI) {
            IIIIllIlIIIllIIIIIIlIlIll.lIlIlIIIIllllIIlllllllIlI = false;
            IIIIllIlIIIllIIIIIIlIlIll.llIllllllIllllllllIllIIll = 1.0f;
            this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIlIlIIllIIIIlIlIlIl = false;
            this.lllIIllllIIlIIIlIIIIllIlI = new llllIIlllIlIlIIlIIlllIIII();
            this.lllIllIllIIIIllIIlIIlIlll = new llllIIlllIlIlIIlIIlllIIII();
            this.llIIIIIIlllIlIIlIlIIllIII.lIllllIIllIllllllIllIIIll.llIIIllIIlIIIlIllIllIIlII = true;
        }
        if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lllIIIlIlllllIIIIlIllIIll() <= 0.0f) {
            float f3 = (float)((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).IIlIIIIIIIIlIIIIIlIlIllII + f;
            f2 /= (1.0f - 2.25f * 222.22223f / (f3 + 798.0769f * 0.62650603f)) * 2.0f + 1.0f;
        }
        if ((illIIIIIIIlllIIIlIlIlIlll = IIIlIlllIIlIIIlIllIIlIlll.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII, lllIIIIIlllIIlIllIIlIIIlI2, f)).llIIIllIIlIIIlIllIllIIlII() == lIlIlIIIIIlllIllIIIlIIlIl.lIIllIIlIIIllIlIIllIIlIll) {
            f2 = f2 * (10.736842f * 5.5882354f) / (1.075f * 65.11628f);
        }
        return f2;
    }

    public void lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2) {
        if (IIlllIIlIllIIIIllIlIIIlll.lIIIlIIIlIllIlllIIIIIlIlI() && IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI) {
            if (this.lIlIlIllllIIlllIIIllllIlI != null) {
                this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII();
            }
            this.llIIlIlIllIlIIIllIllllIlI(lIIlIllIlIIllIIllIlIlllIl2);
        }
    }

    public void IIIlIlIIlllllIIIlllIllIll() {
        if (IIlllIIlIllIIIIllIlIIIlll.IIlIlIllllllIllllIIIIIllI && lllllIIIlIlIIIlIIllIIIIIl.llIIlIlIllIlIIIllIllllIlI() == null) {
            lllllIIIlIlIIIlIIllIIIIIl.lllIllIllIlIIIlllIIllllII();
        }
        this.IlIllllIllllllllIIIlIIlII();
        this.IllIIllIlIlIlIlllIlIlIllI();
        this.llIllllIlIllIlIlIIllIlIII = this.lIllIllIIllIlllIIIlllIIIl;
        this.llllIllIllIlIlIlIIIlIllll = this.lIlIIllIlIlIIlIlllIIllIII;
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIlIlIIllIIIIlIlIlIl) {
            float f = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII * (1.018868f * 0.5888889f) + 0.8363636f * 0.23913044f;
            float f2 = f * f * f * (67.63637f * 0.11827957f);
            this.lIllIllllIllIlIIIllIIllll = this.lllIIllllIIlIIIlIIIIllIlI.lllIllIllIlIIIlllIIllllII(this.IIIIlllIIIlIlIlIIIIIlllIl, 0.27777776f * 0.18f * f2);
            this.IIIIIIllIlIIIIlIlllIllllI = this.lllIllIllIIIIllIIlIIlIlll.lllIllIllIlIIIlllIIllllII(this.lIlIlIIIIlIlIlllIIIllllIl, 2.6296296f * 0.019014085f * f2);
            this.IIlIIIllIIIlIlllIIIIllllI = 0.0f;
            this.IIIIlllIIIlIlIlIIIIIlllIl = 0.0f;
            this.lIlIlIIIIlIlIlllIIIllllIl = 0.0f;
        } else {
            this.lIllIllllIllIlIIIllIIllll = 0.0f;
            this.IIIIIIllIlIIIIlIlllIllllI = 0.0f;
            this.lllIIllllIIlIIIlIIIIllIlI.lllIllIllIlIIIlllIIllllII();
            this.lllIllIllIIIIllIIlIIlIlll.lllIllIllIlIIIlllIIllllII();
        }
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() == null) {
            this.llIIIIIIlllIlIIlIlIIllIII.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll);
        }
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        double d = lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll;
        double d2 = lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl + (double)lllIIIIIlllIIlIllIIlIIIlI2.llllIllIllllIlIlIIIIlIlll();
        double d3 = lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII;
        float f = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.llIllllllIllllllllIllIIll(new llllIllIllllIlIlllllIlIlI(d, d2, d3));
        float f3 = (float)this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IllllIllIIIlllIIllllllIII / (8.685715f * 1.8421053f);
        f3 = llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(f3, 0.0f, 1.0f);
        float f4 = f * (1.0f - f3) + f3;
        this.lIllIllIIllIlllIIIlllIIIl += (f4 - this.lIllIllIIllIlllIIIlllIIIl) * (0.18275863f * 0.5471698f);
        this.IIlIIIIIIlllllllllIIIIIII += 0xFFFFE649 & 0x983;
        this.IllllIllIIIlllIIllllllIII.lllIllIllIlIIIlllIIllllII();
        this.IIlllllllllIlIllIlIlIIllI();
        this.IIllIIIIIlIIIlIllIlIIllII = this.IIIllIIIlllIIIIlIlIIIIlIl;
        if (IIllIIlIIIllIIllllIIlllII.IllllIllIIIlllIIllllllIII) {
            this.IIIllIIIlllIIIIlIlIIIIlIl += 0.07826087f * 0.6388889f;
            if (this.IIIllIIIlllIIIIlIlIIIIlIl > 1.0f) {
                this.IIIllIIIlllIIIIlIlIIIIlIl = 1.0f;
            }
            IIllIIlIIIllIIllllIIlllII.IllllIllIIIlllIIllllllIII = false;
        } else if (this.IIIllIIIlllIIIIlIlIIIIlIl > 0.0f) {
            this.IIIllIIIlllIIIIlIlIIIIlIl -= 0.0147388065f * 0.84810126f;
        }
    }

    public void IIIlIlIIlllllIIIlllIllIll(float f) {
        Object object;
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        lIIIIIIllllllIlIlllIIlllI lIIIIIIllllllIlIlllIIlllI2 = new lIIIIIIllllllIlIlllIIlllI(lllIIIIIlllIIlIllIIlIIIlI2);
        lllIIIIIlIIlIIIIIlIllIlll.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII(lIIIIIIllllllIlIlllIIlllI2);
        float f2 = lllIIIIIlllIIlIllIIlIIIlI2.llllIllIllllIlIlIIIIlIlll();
        double d = lllIIIIIlllIIlIllIIlIIIlI2.IIIIIIllIlIIIIlIlllIllllI + (lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll - lllIIIIIlllIIlIllIIlIIIlI2.IIIIIIllIlIIIIlIlllIllllI) * (double)f;
        double d2 = lllIIIIIlllIIlIllIIlIIIlI2.IIlIIIllIIIlIlllIIIIllllI + (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl - lllIIIIIlllIIlIllIIlIIIlI2.IIlIIIllIIIlIlllIIIIllllI) * (double)f + (double)f2;
        double d3 = lllIIIIIlllIIlIllIIlIIIlI2.IIIIllIIIIIlIlIlllIIllIll + (lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII - lllIIIIIlllIIlIllIIlIIIlI2.IIIIllIIIIIlIlIlllIIllIll) * (double)f;
        if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IllIlIIlIIllIlllIlIlIIlIl && ((IllIlIIlIIllIlllIlIlIIlIl)lllIIIIIlllIIlIllIIlIIIlI2).lIlllllIIIIIlllllIIlIllII()) {
            f2 = (float)((double)f2 + 1.0);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, 29.333334f * 0.010227273f, 0.0f);
            if (!this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIIlIlIlIIlIllllIIlIIIII) {
                object = new llllIllIllllIlIlllllIlIlI(lllIIIIIlllIIlIllIIlIIIlI2);
                lllIIIIIIllIlllIlIIlIlIll lllIIIIIIllIlllIlIIlIlIll2 = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.llIIlIlIllIlIIIllIllllIlI((llllIllIllllIlIlllllIlIlI)object);
                IllIIIIIIIlllIIIlIlIlIlll illIIIIIIIlllIIIlIlIlIlll = lllIIIIIIllIlllIlIIlIlIll2.IllllIllIIIlllIIllllllIII();
                if (illIIIIIIIlllIIIlIlIlIlll == lIIIllllllllllIllIllIlIIl.IIIIllIIIIIlIlIlllIIllIll) {
                    int n = ((llIlllIllIllIIIlllIIIIlll)((Object)lllIIIIIIllIlllIlIIlIlIll2.lllIllIllIlIIIlllIIllllII(lIIllIIlIIIIIIIllIllIlIIl.lIIIlIIIIIIIlIIIllIIIlIII))).IllllIllIIIlllIIllllllIII();
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI((float)(n * (0x4A5B & 0xFFFF84FA)), 0.0f, 1.0f, 0.0f);
                }
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(lIIIIIIllllllIlIlllIIlllI2.IlIllIIIIlIllllIlIIlIIlll() + (lIIIIIIllllllIlIlllIIlllI2.llIIlIlIllIlIIIllIllllIlI() - lIIIIIIllllllIlIlllIIlllI2.IlIllIIIIlIllllIlIIlIIlll()) * f + 0.41935483f * 429.23077f, 0.0f, -2.9130435f * 0.3432836f, 0.0f);
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(lIIIIIIllllllIlIlllIIlllI2.IIIlIlIIlllllIIIlllIllIll() + (lIIIIIIllllllIlIlllIIlllI2.IllllIllIIIlllIIllllllIII() - lIIIIIIllllllIlIlllIIlllI2.IIIlIlIIlllllIIIlllIllIll()) * f, 1.9767442f * -0.5058824f, 0.0f, 0.0f);
            }
        } else if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIIIlIllIlllIIIIIIlllII > 0) {
            double d4 = this.llllIllIllIlIlIlIIIlIllll + (this.lIlIIllIlIlIIlIlllIIllIII - this.llllIllIllIlIlIlIIIlIllll) * f;
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIIlIlIlIIlIllllIIlIIIII) {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, 0.0f, (float)(-d4));
            } else {
                float f3 = lIIIIIIllllllIlIlllIIlllI2.llIIlIlIllIlIIIllIllllIlI();
                float f4 = lIIIIIIllllllIlIlllIIlllI2.IllllIllIIIlllIIllllllIII();
                if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIIIlIllIlllIIIIIIlllII == (0xFFFF8617 & 0x290A)) {
                    f4 += 441.81818f * 0.4074074f;
                }
                double d5 = (double)(-llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(f3 / (0.80487806f * 223.63637f) * (4.2725663f * 0.7352941f)) * llIllIIlIllllllIlllIlIlIl.llIIlIlIllIlIIIllIllllIlI(f4 / (2.148148f * 83.793106f) * (3.5714285f * 0.879646f))) * d4;
                double d6 = (double)(llIllIIlIllllllIlllIlIlIl.llIIlIlIllIlIIIllIllllIlI(f3 / (131.42857f * 1.3695652f) * (4.3196898f * 0.72727275f)) * llIllIIlIllllllIlllIlIlIl.llIIlIlIllIlIIIllIllllIlI(f4 / (200.0f * 0.9f) * (15.0f * 0.20943952f))) * d4;
                double d7 = (double)(-llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(f4 / (0.24358974f * 738.9474f) * (2.090909f * 1.5025009f))) * d4;
                for (int i = 0x4118 & 0xFFFFAE61; i < (0xFFFF8A08 & 0x6008); ++i) {
                    double d8;
                    llIIIllllllIIllIllIIllIlI llIIIllllllIIllIllIIllIlI2;
                    float f5 = (i & (0x1B49 & 0x413)) * (0x2A & 0x6282) - (0xFFFFE701 & 0x1003);
                    float f6 = (i >> (0x2503 & 0xFFFF8009) & (0x15 & 0x42A9)) * (0xFFFFA20A & 0x1B7) - (0x3C8F & 0x41);
                    float f7 = (i >> (0xFFFFA022 & 0x510A) & (0x3045 & 0x4601)) * (0x2183 & 0x5012) - (0x401 & 0x2A15);
                    if ((llIIIllllllIIllIllIIllIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII(new IlIIIIIlllllllIIIIlllIIll(d + (double)(f5 *= 0.6041667f * 0.16551724f), d2 + (double)(f6 *= 1.0980393f * 0.09107143f), d3 + (double)(f7 *= 0.015789473f * 6.3333335f)), new IlIIIIIlllllllIIIIlllIIll(d - d5 + (double)f5 + (double)f7, d2 - d7 + (double)f6, d3 - d6 + (double)f7), (0x410 & 0x4224) != 0, (0x6081 & 1) != 0, (0x1050 & 0xFFFFA300) != 0)) == null || this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.e_() || !((d8 = llIIIllllllIIllIllIIllIlI2.IllllIllIIIlllIIllllllIII.llIIIllIIlIIIlIllIllIIlII(new IlIIIIIlllllllIIIIlllIIll(d, d2, d3))) < d4)) continue;
                    d4 = d8;
                }
                if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIIIlIllIlllIIIIIIlllII == (0x1812 & 0x4682)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(92.57143f * 1.9444444f, 0.0f, 1.0f, 0.0f);
                }
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(lIIIIIIllllllIlIlllIIlllI2.IllllIllIIIlllIIllllllIII() - f4, 1.0f, 0.0f, 0.0f);
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(lIIIIIIllllllIlIlllIIlllI2.llIIlIlIllIlIIIllIllllIlI() - f3, 0.0f, 1.0f, 0.0f);
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, 0.0f, (float)(-d4));
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f3 - lIIIIIIllllllIlIlllIIlllI2.llIIlIlIllIlIIIllIllllIlI(), 0.0f, 1.0f, 0.0f);
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f4 - lIIIIIIllllllIlIlllIIlllI2.IllllIllIIIlllIIllllllIII(), 1.0f, 0.0f, 0.0f);
            }
        } else {
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, 0.0f, -0.20434783f * 0.4893617f);
        }
        if (!this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIIlIlIlIIlIllllIIlIIIII) {
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(lIIIIIIllllllIlIlllIIlllI2.IIIlIlIIlllllIIIlllIllIll() + (lIIIIIIllllllIlIlllIIlllI2.IllllIllIIIlllIIllllllIII() - lIIIIIIllllllIlIlllIIlllI2.IIIlIlIIlllllIIIlllIllIll()) * f, 1.0f, 0.0f, 0.0f);
            if (lllIIIIIlllIIlIllIIlIIIlI2 instanceof IlllIllIIlIIlIlIIIlllIIll) {
                object = (IlllIllIIlIIlIlIIIlllIIll)lllIIIIIlllIIlIllIIlIIIlI2;
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(((IlllIllIIlIIlIlIIIlllIIll)object).IIllIlIIlIlIIIllIIlIlIIlI + (((IlllIllIIlIIlIlIIIlllIIll)object).llIIlIllIlIlIIIIIIlIlIIIl - ((IlllIllIIlIIlIlIIIlllIIll)object).IIllIlIIlIlIIIllIIlIlIIlI) * f + 0.82417583f * 218.4f, 0.0f, 1.0f, 0.0f);
            } else {
                lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(lIIIIIIllllllIlIlllIIlllI2.IlIllIIIIlIllllIlIIlIIlll() + (lIIIIIIllllllIlIlllIIlllI2.llIIlIlIllIlIIIllIllllIlI() - lIIIIIIllllllIlIlllIIlllI2.IlIllIIIIlIllllIlIIlIIlll()) * f + 1.0f * 180.0f, 0.0f, 1.0f, 0.0f);
            }
        }
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, -f2, 0.0f);
        d = lllIIIIIlllIIlIllIIlIIIlI2.IIIIIIllIlIIIIlIlllIllllI + (lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll - lllIIIIIlllIIlIllIIlIIIlI2.IIIIIIllIlIIIIlIlllIllllI) * (double)f;
        d2 = lllIIIIIlllIIlIllIIlIIIlI2.IIlIIIllIIIlIlllIIIIllllI + (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl - lllIIIIIlllIIlIllIIlIIIlI2.IIlIIIllIIIlIlllIIIIllllI) * (double)f + (double)f2;
        d3 = lllIIIIIlllIIlIllIIlIIIlI2.IIIIllIIIIIlIlIlllIIllIll + (lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII - lllIIIIIlllIIlIllIIlIIIlI2.IIIIllIIIIIlIlIlllIIllIll) * (double)f;
        this.lllIlIllllIlIIllIIIlIlllI = this.llIIIIIIlllIlIIlIlIIllIII.lIllllIIllIllllllIllIIIll.lllIllIllIlIIIlllIIllllII(d, d2, d3, f);
    }

    public float lllIllIllIlIIIlllIIllllII(IllIlIIlIIllIlllIlIlIIlIl illIlIIlIIllIlllIlIlIIlIl, float f) {
        int n = illIlIIlIIllIlllIlIlIIlIl.llIIlIlIllIlIIIllIllllIlI(lIIIlllIllIIIlIlIIllIIlIl.IIlIIIIIIlllllllllIIIIIII).llIIlIlIllIlIIIllIllllIlI();
        return n > (0xFFFFE9FC & 0xCA) ? 1.0f : 0.83802813f * 0.8352941f + llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(((float)n - f) * (1.0053097f * 3.125f) * (0.14814815f * 1.35f)) * (0.58536583f * 0.51250005f);
    }

    public void IllllIllIIIlllIIllllllIII() {
        this.IIlIIlIIlIlIllIlIllIlIIIl = !this.IIlIIlIIlIlIllIlIllIlIIIl;
    }

    public void lIllllIIllIllllllIllIIIll(float f) {
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lIIIIIIIllIIIlIlIllIlIIlI && !this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIIIIlIIIlllIllIIllIllll && !this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.IlIIlIIIIlllIlIllIlIIlIIl() && !this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.llllIllIllIlIlIlIIIlIllll) {
            lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x4B12 & 0x327, 0xFFFF870B & 0x3BC3, 0xFFFFC0ED & 0x3903, 0x7186 & 1);
            GL11.glLineWidth(1.0f);
            lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x30 & 0x42) != 0);
            lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x5745 & 0x1F38);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            this.IIIlIlIIlllllIIIlllIllIll(f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, lllIIIIIlllIIlIllIIlIIIlI2.llllIllIllllIlIlIIIIlIlll(), 0.0f);
            lIIIIllIIIlIllIlIIIlllllI.lllIllIllIlIIIlllIIllllII(new IlIIIIIllIIlIIIlIllIIlIlI(0.0, 0.0, 0.0, 0.3235294117647059 * 0.015454545454545453, 1.4363636363636363E-4 * 0.6962025316455697, 0.04 * 0.0025), 0xFFFF94FF & 0x9FF, 0x2000 & 0xFFFF9230, 0xFFFF8843 & 0x142C, 0xFFFFF5FF & 0x8FF);
            lIIIIllIIIlIllIlIIIlllllI.lllIllIllIlIIIlllIIllllII(new IlIIIIIllIIlIIIlIllIIlIlI(0.0, 0.0, 0.0, 1.5714285714285714 * 6.363636363636364E-5, 1.735294117647059E-4 * 0.576271186440678, 0.06172839506172839 * 0.081), 0xFFFFD210 & 0x542, 0x4900 & 0x16, 0xFF & 0xFFFF80FF, 0x11FF & 0xFFFFECFF);
            lIIIIllIIIlIllIlIIIlllllI.lllIllIllIlIIIlllIIllllII(new IlIIIIIllIIlIIIlIllIIlIlI(0.0, 0.0, 0.0, 1.96078431372549E-6 * 51.0, 1.7142857142857142 * 0.001925, 1.0714285714285714 * 9.333333333333334E-5), 0xFFFFA804 & 0x1C1, 0x9FF & 0x2FF, 0xFFFFB413 & 0x4040, 0x51FF & 0xFF);
            lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x281 & 0x492B) != 0);
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
            lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        }
    }

    public void lllIllIllIlIIIlllIIllllII(int n, float f, long l) {
        IIllIIIllIIlIlllllIlllIII iIllIIIllIIlIlllllIlllIII;
        boolean bl = IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI();
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(n, f, l);
        }
        lIIIIllIIIlIllIlIIIlllllI lIIIIllIIIlIllIlIIIlllllI2 = this.llIIIIIIlllIlIIlIlIIllIII.lIllllIIllIllllllIllIIIll;
        IIlllIIIIIllIllIIIIIIllIl iIlllIIIIIllIllIIIIIIllIl = this.llIIIIIIlllIlIIlIlIIllIII.lllIlIlllIIlIlIIlIlllIIlI;
        boolean bl2 = this.llIIIIIIlllIlIIlIlIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings2.strings[0x437F & 0x10FD]);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(0xFFFF8490 & 0x306C, 0x2490 & 0x180A, this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll, this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll);
        } else {
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0xA32 & 0x10C, 0x305 & 0x2828, this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll, this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll);
        }
        this.lIIllIIlIIIllIlIIllIIlIll(f);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII(0x414A & 0x43A0);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.llllIllIllIlIlIlIIIlIllll();
        }
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings9.strings[0x105F & 0x435E]);
        this.lllIllIllIlIIIlllIIllllII(f, n);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(f);
        }
        IIIlIlllIIlIIIlIllIIlIlll.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll, (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.lllIIIlIllIlllIIIIIIlllII == (0xFFFFB5AA & 0x4243) ? 0xFFFF8345 & 0x2031 : 0x6051 & 0x1082) != 0);
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x26B7 & 0x52F7]);
        llIllIIIIlIIllIlIlIIlIlII.lllIllIllIlIIIlllIIllllII();
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings9.strings[0x3661 & 0xFFFF80E9]);
        lIIllIIIlIIIIlIlIlIlIlIIl lIIllIIIlIIIIlIlIlIlIlIIl2 = new lIIllIIIlIIIIlIlIlIlIlIIl();
        lllIIIIIlllIIlIllIIlIIIlI lllIIIIIlllIIlIllIIlIIIlI2 = this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI();
        double d = lllIIIIIlllIIlIllIIlIIIlI2.lIIIIIIllIllllIIlIIIllIIl + (lllIIIIIlllIIlIllIIlIIIlI2.llIllIlIlIIIIlIIIIllIllll - lllIIIIIlllIIlIllIIlIIIlI2.lIIIIIIllIllllIIlIIIllIIl) * (double)f;
        double d2 = lllIIIIIlllIIlIllIIlIIIlI2.IlllIllIlIIIIIlllIlIIIIll + (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl - lllIIIIIlllIIlIllIIlIIIlI2.IlllIllIlIIIIIlllIlIIIIll) * (double)f;
        double d3 = lllIIIIIlllIIlIllIIlIIIlI2.lIlIlIllllIIlllIIIllllIlI + (lllIIIIIlllIIlIllIIlIIIlI2.IIllIIIIIlIIIlIllIlIIllII - lllIIIIIlllIIlIllIIlIIIlI2.lIlIlIllllIIlllIIIllllIlI) * (double)f;
        if (bl) {
            llIlIIIlIIIlIIIIllllIlIII.lllIllIllIlIIIlllIIllllII(lIIllIIIlIIIIlIlIlIlIlIIl2, d, d2, d3);
        } else {
            lIIllIIIlIIIIlIlIlIlIlIIl2.lllIllIllIlIIIlllIIllllII(d, d2, d3);
        }
        if ((IIIIllIlIIIllIIIIIIlIlIll.lllIIIIIlIIlIIIIIlIllIlll() || IIIIllIlIIIllIIIIIIlIlIll.IIIllllllIllIIIIIIIllIIll() || IIIIllIlIIIllIIIIIIlIlIll.IlIIlIIlIlIIllllIIllIllll()) && !IlllIllIIIlIIIlllIllIIlII.lllllIIIlIIllllllllIlllIl) {
            this.lllIllIllIlIIIlllIIllllII(0xFFFFFFFF & 0xFFFFFFFF, f);
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x23BC & 0xFFFFCEB8]);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1F4D & 0xFFFF9711);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            Project.gluPerspective(this.lllIllIllIlIIIlllIIllllII(f, (0xFFFF8311 & 0x5821) != 0), (float)this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll / (float)this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll, 4.375f * 0.011428571f, this.polyBlurFarPlaneDistance);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x571C & 0x1702);
            if (bl) {
                IlllIllIIIlIIIlllIllIIlII.IIllIIIIIlIIIlIllIlIIllII();
            }
            lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(f, n);
            if (bl) {
                IlllIllIIIlIIIlllIllIIlII.IlIlIIIlIlIIlIIIlllIllIII();
            }
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1707 & 0x1719);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            Project.gluPerspective(this.lllIllIllIlIIIlllIIllllII(f, (0xFFFFC167 & 0x1881) != 0), (float)this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll / (float)this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll, 0.07777778f * 0.64285713f, this.polyBlurFarPlaneDistance);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x570C & 0x3F02);
        } else {
            lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        }
        this.lllIllIllIlIIIlllIIllllII(0x1E & 0x20A0, f);
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(0x1D07 & 0x1F99);
        if (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl + (double)lllIIIIIlllIIlIllIIlIIIlI2.llllIllIllllIlIlIIIIlIlll() < 145.23076923076923 * 0.8813559322033898 + (double)(this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlllIIlIllllIlllIIlIIlIII * (183.65218f * 0.6969697f))) {
            this.lllIllIllIlIIIlllIIllllII(lIIIIllIIIlIllIlIIIlllllI2, f, n);
        }
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x1AB9 & 0x3F9]);
        this.lllIllIllIlIIIlllIIllllII(0xE & 0x1410, f);
        this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(IlIIIlllIlIIllIlIIIIlIlIl.llIIlIlIllIlIIIllIllllIlI);
        lllIIIlIIlIlIllIIIIIlIIll.lllIllIllIlIIIlllIIllllII();
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x6FE & 0x3BA]);
        if (bl) {
            int n2 = this.lIIllIIlIIIllIlIIllIIlIll;
            this.lIIllIIlIIIllIlIIllIIlIll = n2 + (7 & 0x1E09);
            llIlIIIlIIIlIIIIllllIlIII.lllIllIllIlIIIlllIIllllII(lIIIIllIIIlIllIlIIIlllllI2, lllIIIIIlllIIlIllIIlIIIlI2, f, lIIllIIIlIIIIlIlIlIlIlIIl2, n2, this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.e_());
        } else {
            int n3 = this.lIIllIIlIIIllIlIIllIIlIll;
            this.lIIllIIlIIIllIlIIllIIlIll = n3 + (0xFFFFC1E3 & 0x1201);
            lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2, f, lIIllIIIlIIIIlIlIlIlIlIIl2, n3, this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.e_());
        }
        if (n == 0 || n == (0x546 & 0x212)) {
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0xFBB & 0x42BB]);
            IllIIlllIlllIIlIlIIIIIlll.IlIllIIIIlIllllIlIIlIIlll.lllIllIllIlIIIlllIIllllII();
            this.llIIIIIIlllIlIIlIlIIllIII.lIllllIIllIllllllIllIIIll.lllIllIllIlIIIlllIIllllII(l);
            IllIIlllIlllIIlIlIIIIIlll.IlIllIIIIlIllllIlIIlIIlll.llIIlIlIllIlIIIllIllllIlI();
        }
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x12BC & 0x43BD]);
        IllIIlllIlllIIlIlIIIIIlll.lIllllIIllIllllllIllIIIll.lllIllIllIlIIIlllIIllllII();
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIIlIIlIIlIlllIIIIIlIIlI && n > 0) {
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0xFFFF82BD & 0x13FD]);
            GL11.glFinish();
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x17BE & 0xFFFF82BD]);
        }
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x17C4 & 0x1703);
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        if (bl) {
            llIlIIIlIIIlIIIIllllIlIII.lllIllIllIlIIIlllIIllllII();
        }
        lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(IlllIIlllIIlIllIlllIIIllI.lllIllIllIlIIIlllIIllllII, (double)f, n, lllIIIIIlllIIlIllIIlIIIlI2);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        if (bl) {
            llIlIIIlIIIlIIIIllllIlIII.llIIlIlIllIlIIIllIllllIlI();
        }
        lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(IlllIIlllIIlIllIlllIIIllI.llIIlIlIllIlIIIllIllllIlI, (double)f, n, lllIIIIIlllIIlIllIIlIIIlI2);
        this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().llIIlIlIllIlIIIllIllllIlI(IlIIIlllIlIIllIlIIIIlIlIl.llIIlIlIllIlIIIllIllllIlI).llIIlIlIllIlIIIllIllllIlI((0x60E & 0x5970) != 0, (0x50E0 & 0x801) != 0);
        if (bl) {
            llIlIIIlIIIlIIIIllllIlIII.IllllIllIIIlllIIllllllIII();
        }
        lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(IlllIIlllIIlIllIlllIIIllI.IllllIllIIIlllIIllllllIII, (double)f, n, lllIIIIIlllIIlIllIIlIIIlI2);
        this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().llIIlIlIllIlIIIllIllllIlI(IlIIIlllIlIIllIlIIIIlIlIl.llIIlIlIllIlIIIllIllllIlI).IllllIllIIIlllIIllllllIII();
        if (bl) {
            llIlIIIlIIIlIIIIllllIlIII.IlIllIIIIlIllllIlIIlIIlll();
        }
        IllIIlllIlllIIlIlIIIIIlll.lIllllIIllIllllllIllIIIll.llIIlIlIllIlIIIllIllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(0xFFFF9D02 & 0x5D4C);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x644 & 0x5A06, 0.025252525f * 3.96f);
        if (!this.IIlIlIlllllIllllIllllIllI) {
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x3700 & 0x17A8);
            lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
            lllIIIlIIlIlIllIIIIIlIIll.llIIlIlIllIlIIIllIllllIlI();
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings8.strings[0xFFFFACDF & 0x425F]);
            lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2, lIIllIIIlIIIIlIlIlIlIlIIl2, f);
            lllIIIlIIlIlIllIIIIIlIIll.lllIllIllIlIIIlllIIllllII();
            this.lIllllIIllIllllllIllIIIll();
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x17C0 & 0x3723);
            lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
            if (this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI != null && lllIIIIIlllIIlIllIIlIIIlI2.lllIllIllIlIIIlllIIllllII(lIlIlIIIIIlllIllIIIlIIlIl.lIIllIIlIIIllIlIIllIIlIll) && bl2) {
                iIllIIIllIIlIlllllIlllIII = (IIllIIIllIIlIlllllIlllIII)lllIIIIIlllIIlIllIIlIIIlI2;
                lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
                this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings2.strings[0xA1 & 0xFFFF88F8]);
                lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(iIllIIIllIIlIlllllIlllIII, this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI, 0xFFFFD580 & 0xE, f);
                lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
            }
        }
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x5F98 & 0xFFFF9707);
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
        if (bl2 && this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI != null && !lllIIIIIlllIIlIllIIlIIIlI2.lllIllIllIlIIIlllIIllllII(lIlIlIIIIIlllIllIIIlIIlIl.lIIllIIlIIIllIlIIllIIlIll)) {
            iIllIIIllIIlIlllllIlllIII = (IIllIIIllIIlIlllllIlllIII)lllIIIIIlllIIlIllIIlIIIlI2;
            lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings2.strings[0xFFFFD5EC & 0x2A0]);
            lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(iIllIIIllIIlIlllllIlllIII, this.llIIIIIIlllIlIIlIlIIllIII.lllIIllllIIlIIIlIIIIllIlI, 0xC4 & 0x21, f);
            lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        }
        if (!lIIIIllIIIlIllIlIIIlllllI2.IlIllIIIIlIllllIlIIlIIlll.isEmpty()) {
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x42BE & 0x16FF]);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x1B52 & 0x7A2, 1 & 0xFFFF8991, 0x455B & 0x801, 0x20B6 & 0x1408);
            this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().llIIlIlIllIlIIIllIllllIlI(IlIIIlllIlIIllIlIIIIlIlIl.llIIlIlIllIlIIIllIllllIlI).llIIlIlIllIlIIIllIllllIlI((0x5434 & 0xA49) != 0, (0x1080 & 0xFFFFA020) != 0);
            lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII(), IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII().IllllIllIIIlllIIllllllIII(), lllIIIIIlllIIlIllIIlIIIlI2, f);
            this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().llIIlIlIllIlIIIllIllllIlI(IlIIIlllIlIIllIlIIIIlIlIl.llIIlIlIllIlIIIllIllllIlI).IllllIllIIIlllIIllllllIII();
            lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x3A3 & 0x4352, 0xFFFFDB43 & 0x2707, 0xFFFF8483 & 0x169, 1 & 0x6118);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        if (!this.IIlIlIlllllIllllIllllIllI) {
            this.lIIllIIlIIIllIlIIllIIlIll();
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0xFFFF92BF & 0x47BF]);
            if (bl) {
                IlllIllIIIlIIIlllIllIIlII.llIllllIlIllIlIlIIllIlIII();
            }
            iIlllIIIIIllIllIIIIIIllIl.llIIlIlIllIlIIIllIllllIlI(lllIIIIIlllIIlIllIIlIIIlI2, f);
            lllIIIlIIlIlIllIIIIIlIIll.lllIllIllIlIIIlllIIllllII();
            this.lllIllIllIlIIIlllIIllllII(0x2080 & Short.MIN_VALUE, f);
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings1.strings[0x1CB & 0xFFFF97EE]);
            if (bl) {
                IlllIllIIIlIIIlllIllIIlII.lIllIllIIllIlllIIIlllIIIl();
            }
            iIlllIIIIIllIllIIIIIIllIl.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2, f);
            if (bl) {
                IlllIllIIIlIIIlllIllIIlII.IIIIIlIIIllIllllIIlllIIII();
            }
            this.lIllllIIllIllllllIllIIIll();
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x5456 & 0x900) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x12F1 & 0xFC2]);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.llIllllllIIIIIIlIllIlIllI();
        }
        this.llIIlIlIllIlIIIllIllllIlI(f);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.lIIIIIIllIllllIIlIIIllIIl();
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0xFFFFA4AF & 0x1A01) != 0);
        lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(lllIIIIIlllIIlIllIIlIIIlI2, f);
        if (bl) {
            llIlIIIlIIIlIIIIllllIlIII.lllIllIllIlIIIlllIIllllII(this, f, n);
            IlllIllIIIlIIIlllIllIIlII.IlllIllIlIIIIIlllIlIIIIll();
        }
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x307 & 0x79A, 0x2B03 & 0x7BB, 0xFFFF8283 & 0x5165, 0x403 & 0xFFFFC01C);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0xFFFFD204 & 0x26F6, 0.8815789f * 0.11343284f);
        this.lllIllIllIlIIIlllIIllllII(0xFFFF8902 & 0x40DC, f);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((4 & 0xFFFFE460) != 0);
        this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(IlIIIlllIlIIllIlIIIIlIlIl.llIIlIlIllIlIIIllIllllIlI);
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(0x1F23 & 0x1D49);
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0xFFFF83E1 & 0x2D7]);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.lIlIlIllllIIlllIIIllllIlI();
        }
        lIIIIllIIIlIllIlIIIlllllI2.lllIllIllIlIIIlllIIllllII(IlllIIlllIIlIllIlllIIIllI.IlIllIIIIlIllllIlIIlIIlll, (double)f, n, lllIIIIIlllIIlIllIIlIIIlI2);
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.llllIllIllIIIlllIIlllIIll();
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(0xFFFFBF04 & 0x1DC0);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x2911 & 0x89) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.IIlllllllllIlIllIlIlIIllI();
        if (lllIIIIIlllIIlIllIIlIIIlI2.IIIllIIIlllIIIIlIlIIIIlIl + (double)lllIIIIIlllIIlIllIIlIIIlI2.llllIllIllllIlIlIIIIlIlll() >= 0.7916666666666666 * 161.6842105263158 + (double)(this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlllIIlIllllIlllIIlIIlIII * (39.384617f * 3.25f))) {
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x2C2 & 0x2F6]);
            this.lllIllIllIlIIIlllIIllllII(lIIIIllIIIlIllIlIIIlllllI2, f, n);
        }
        lllIIIIIlIIlIIIIIlIllIlll.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII(new IlIIlIIlIlIIllllIIllIllll(f));
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0x2FD3 & 0xFFFF92C7]);
        int n4 = 0x5310 & 0xFFFFA000;
        if (n4 == 0 && this.lIlllIIIlIIlIIIlIlIIIIlII && !IlllIllIIIlIIIlllIllIIlII.lllllIIIlIIllllllllIlllIl) {
            if (bl) {
                llIlIIIlIIIlIIIIllllIlIII.llIIlIlIllIlIIIllIllllIlI(this, f, n);
                IlllIllIIIlIIIlllIllIIlII.llIllIlIlIIIIlIIIIllIllll();
            }
            lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII(0x934 & 0xFFFF9100);
            if (bl) {
                llIlIIIlIIIlIIIIllllIlIII.IllllIllIIIlllIIllllllIII(this, f, n);
            } else {
                this.llIIlIlIllIlIIIllIllllIlI(f, n);
            }
            this.lIllllIIllIllllllIllIIIll(f);
        }
        if (bl) {
            IlllIllIIIlIIIlllIllIIlII.IIIllIIIlllIIIIlIlIIIIlIl();
        }
    }

    public void lIllllIIllIllllllIllIIIll() {
        lIIllIlIIlllIlIlIlllIlIlI.lIllllIIllIllllllIllIIIll(IIlllIIlIllIIIIllIlIIIlll.IIlIIIIIIlllllllllIIIIIII);
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.lIllllIIllIllllllIllIIIll(IIlllIIlIllIIIIllIlIIIlll.IIIlIllIlIIlIlIIIlIlIlIll);
        if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
            IlllIllIIIlIIIlllIllIIlII.lIlIIIllllIlllIlIllllIlll();
        }
    }

    public static /* synthetic */ lIIIIIIllIIIIIIIlllllllII lllIllIllIlIIIlllIIllllII(lIIlIlIllIIIIlIlIlllIIIII lIIlIlIllIIIIlIlIlllIIIII2) {
        return lIIlIlIllIIIIlIlIlllIIIII2.llIIIIIIlllIlIIlIlIIllIII;
    }

    public void lIIllIIlIIIllIlIIllIIlIll() {
        lIIllIlIIlllIlIlIlllIlIlI.lIllllIIllIllllllIllIIIll(IIlllIIlIllIIIIllIlIIIlll.IIlIIIIIIlllllllllIIIIIII);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x17A2 & 0x3F46);
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        float f = 1.3953488f * 0.0027994793f;
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(f, f, f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(2.2702703f * 3.5238094f, 13.68421f * 0.5846154f, 1.4920635f * 5.361702f);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x170A & 0x7F00);
        this.llIIIIIIlllIlIIlIlIIllIII.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(this.lIIIlIIIIIIIlIIIllIIIlIII);
        GL11.glTexParameteri(0xDF1 & 0xFFFFCFE7, 0xFFFFA8A1 & 0x2A03, 0x3601 & 0xFFFFE72D);
        GL11.glTexParameteri(0xFFFF9FE3 & 0xDE1, 0x2802 & 0x292C, 0xFFFFB609 & 0x2763);
        GL11.glTexParameteri(0x1FF1 & 0xFFFFEDED, 0xFFFFAE46 & 0x2822, 0x3D00 & 0x2930);
        GL11.glTexParameteri(0xDF1 & 0xDE3, 0x38C3 & 0xFFFFAB1B, 0x2910 & 0x7B2C);
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(1.0f, 1.0f, 1.0f, 1.0f);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.lIllllIIllIllllllIllIIIll(IIlllIIlIllIIIIllIlIIIlll.IIIlIllIlIIlIlIIIlIlIlIll);
        if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
            IlllIllIIIlIIIlllIllIIlII.IllIlllIIIlllllIllIIlIlIl();
        }
    }

    public void llIIlIlIllIlIIIllIllllIlI(float f, long l) {
        this.llIIIllIIlIIIlIllIllIIlII(f);
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI() == null) {
            this.llIIIIIIlllIlIIlIlIIllIII.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll);
        }
        this.lllIllIllIlIIIlllIIllllII(f);
        if (IIIIllIlIIIllIIIIIIlIlIll.lIIIIIIllllllIlIlllIIlllI()) {
            IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII, f, l);
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x4206 & 0x3E14, 0.125f * 0.8f);
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII(Strings13.strings[0x2B7 & 0xAB6]);
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll) {
            llIIlIlIllIlIIIllIllllIlI = 0xFFFFA064 & 0xE0A;
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x962 & 0x14) != 0, (0x4219 & 0xD21) != 0, (0x3015 & 0xFFFFC629) != 0, (0x4C64 & 0x1188) != 0);
            this.lllIllIllIlIIIlllIIllllII(0x7450 & 0xFFFF80AD, f, l);
            llIIlIlIllIlIIIllIllllIlI = 0x42B & 0xFFFF9081;
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0xE05 & 0x1119) != 0, (0x6904 & 0x1440) != 0, (0x511 & 0x12C2) != 0, (0x2200 & 0x5826) != 0);
            this.lllIllIllIlIIIlllIIllllII(0x549 & 0x283, f, l);
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x5021 & 0x781) != 0, (0xA5 & 0x4809) != 0, (0x1805 & 0xFFFFE199) != 0, (0x2809 & 0xFFFFC704) != 0);
        } else {
            this.lllIllIllIlIIIlllIIllllII(2 & 0x1012, f, l);
        }
        this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.llIIlIlIllIlIIIllIllllIlI();
    }

    public void lllIllIllIlIIIlllIIllllII(float f, int n) {
        float f2;
        this.llIllllllIllllllllIllIIll = this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IllllIllIIIlllIIllllllIII * (0xFFFF92B4 & 0x2953);
        if (IIIIllIlIIIllIIIIIIlIlIll.lllllIIIlIIllllllllIlllIl()) {
            this.llIllllllIllllllllIllIIll *= 0.36363637f * 2.6125f;
        }
        if (IIIIllIlIIIllIIIIIIlIlIll.lllIIllllIIlIIIlIIIIllIlI()) {
            this.llIllllllIllllllllIllIIll *= 0.6701031f * 1.2386154f;
        }
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1701 & 0x3781);
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        float f3 = 1.316f * 0.05319149f;
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll) {
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI((float)(-(n * (0xFFFF9402 & 0x6002) - (0xFFFFA243 & 0x4029))) * f3, 0.0f, 0.0f);
        }
        this.polyBlurFarPlaneDistance = this.llIllllllIllllllllIllIIll * 2.0f;
        if (this.polyBlurFarPlaneDistance < 1.5517242f * 111.488884f) {
            this.polyBlurFarPlaneDistance = 2.9f * 59.65517f;
        }
        if (this.llIIIIIIlllIlIIlIlIIllIII.llIIIllIIlIIIlIllIllIIlII.llIllllllIllllllllIllIIll.IIIlIllIlIIlIlIIIlIlIlIll() == (0xC57 & 0x2381)) {
            this.polyBlurFarPlaneDistance = 1.4482758f * 176.76192f;
        }
        if (this.llIllllllIIIIIIlIllIlIllI != 1.0) {
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI((float)this.lIIIIIIllIllllIIlIIIllIIl, (float)(-this.IlllIllIlIIIIIlllIlIIIIll), 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(this.llIllllllIIIIIIlIllIlIllI, this.llIllllllIIIIIIlIllIlIllI, 1.0);
        }
        Project.gluPerspective(this.lllIllIllIlIIIlllIIllllII(f, (0x4003 & 0xFFFFB071) != 0), (float)this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll / (float)this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll, 0.029411765f * 1.7f, this.polyBlurFarPlaneDistance);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0xFFFFD703 & 0x1F14);
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IIIlIlIIlllllIIIlllIllIll) {
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI((float)(n * (0xFFFF9402 & 0x6B03) - (0xFFFFB4C1 & 0x125)) * (0.39f * 0.25641027f), 0.0f, 0.0f);
        }
        this.IllllIllIIIlllIIllllllIII(f);
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IlIllIIIIlIllllIlIIlIIlll) {
            this.IlIllIIIIlIllllIlIIlIIlll(f);
        }
        if ((f2 = this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.IllIIllIlIlIlIlllIlIlIllI + (this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.IlIllllIllllllllIIIlIIlII - this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.IllIIllIlIlIlIlllIlIlIllI) * f) > 0.0f) {
            int n2 = 0x35 & 0xFFFFA194;
            if (this.llIIIIIIlllIlIIlIlIIllIII.lIIllIIlIIIllIlIIllIIlIll.lllIllIllIlIIIlllIIllllII(lIIIlllIllIIIlIlIIllIIlIl.IlIllllIllllllllIIIlIIlII)) {
                n2 = 0x1407 & 0x2E7;
            }
            float f4 = 1.1111112f * 4.5f / (f2 * f2 + 8.888889f * 0.5625f) - f2 * (1.2857143f * 0.03111111f);
            f4 *= f4;
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(((float)this.IIlIIIIIIlllllllllIIIIIII + f) * (float)n2, 0.0f, 1.0f, 1.0f);
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(1.0f / f4, 1.0f, 1.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-((float)this.IIlIIIIIIlllllllllIIIIIII + f) * (float)n2, 0.0f, 1.0f, 1.0f);
        }
        this.IIIlIlIIlllllIIIlllIllIll(f);
        if (this.IIlIlIlllllIllllIllllIllI) {
            switch (this.IIIIIlIIIllIllllIIlllIIII) {
                case 0: {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.75f * 120.0f, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case 1: {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.83950615f * 214.41177f, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case 2: {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-338.82352f * 0.265625f, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case 3: {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(200.25f * 0.4494382f, 1.0f, 0.0f, 0.0f);
                    break;
                }
                case 4: {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(1.4565217f * -61.791046f, 1.0f, 0.0f, 0.0f);
                }
            }
        }
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(IlIIIIIlIIIIIIIlllIlIllII ilIIIIIlIIIIIIIlllIlIllII) {
        if (this.lIlIlIllllIIlllIIIllllIlI != null) {
            this.lIlIlIllllIIlllIIIllllIlI.lllIllIllIlIIIlllIIllllII();
        }
        this.lIlIlIllllIIlllIIIllllIlI = null;
        if (this.IIIIlIllIlIIllIllIlIIlllI != lIllllIIllIllllllIllIIIll) {
            this.llIIlIlIllIlIIIllIllllIlI(llllIllIllIIIlllIIlllIIll[this.IIIIlIllIlIIllIllIlIIlllI]);
        } else {
            this.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.llIllllllIIIIIIlIllIlIllI());
        }
    }

    public void lllIllIllIlIIIlllIIllllII(lIIIIllIIIlIllIlIIIlllllI lIIIIllIIIlIllIlIIIlllllI2, float f, int n) {
        if (this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII.IllllIllIIIlllIIllllllIII >= (0x2C & 0xFFFFD514) && !IIIIllIlIIIllIIIIIIlIlIll.IIlIIIllIIIlIlllIIIIllllI() && IlllIllIIIlIIIlllIllIIlII.lllIllIllIlIIIlllIIllllII(this.llIIIIIIlllIlIIlIlIIllIII.lIlIIllIlIlIIlIlllIIllIII)) {
            this.llIIIIIIlllIlIIlIlIIllIII.IIIIllIIIIIlIlIlllIIllIll.IllllIllIIIlllIIllllllIII(Strings13.strings[0xAC4 & 0x72C5]);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0xFFFFDFD1 & 0x1701);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            Project.gluPerspective(this.lllIllIllIlIIIlllIIllllII(f, (0x1621 & 5) != 0), (float)this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll / (float)this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll, 0.23421054f * 0.21348314f, this.polyBlurFarPlaneDistance * (8.413794f * 0.47540984f));
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0xFFFF9F60 & 0x1789);
            lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
            this.lllIllIllIlIIIlllIIllllII(0 & 0x4399, f);
            lIIIIllIIIlIllIlIIIlllllI2.llIIlIlIllIlIIIllIllllIlI(f, n);
            lIIllIlIIlllIlIlIlllIlIlI.IIlllllllllIlIllIlIlIIllI();
            lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x17A5 & 0x1F03);
            lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
            Project.gluPerspective(this.lllIllIllIlIIIlllIIllllII(f, (0x4C03 & 0x38D) != 0), (float)this.llIIIIIIlllIlIIlIlIIllIII.IlIllIIIIlIllllIlIIlIIlll / (float)this.llIIIIIIlllIlIIlIlIIllIII.IIIlIlIIlllllIIIlllIllIll, 0.057446808f * 0.8703704f, this.polyBlurFarPlaneDistance);
            lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0xFFFF9789 & 0x5700);
        }
    }
}
