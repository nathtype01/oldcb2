/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : équivalent de GuiMainMenu (confirmé via l'import RealmsBridge et
 * le texte "copyright mojang ab do not distribute" dessiné en bas à droite)
 * les deux textes du bas (build id en bas à gauche, copyright en bas à
 * droite) sont maintenant des littéraux en dur à la demande de l'utilisateur
 * au lieu de venir des tables Strings14/Strings15/Strings9 d'origine
 */
import com.google.common.collect.Lists;
import generated.Strings0;
import generated.Strings11;
import generated.Strings14;
import generated.Strings15;
import generated.Strings3;
import generated.Strings7;
import generated.Strings9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.realms.RealmsBridge;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class llIIIIlIlIIllIlIIIIIlIlll
extends IlIllllIIlIIllIlIlllllIlI
implements IIlIlIllIllIIIllIIIlllIII {
    public String lIllIllllIllIlIIIllIIllll;
    public lIIlIlIlIlIllIIlIIllllIll lllIllIIlllIllIlllIIlllII;
    public int IlIlIIIlIlIIlIIIlllIllIII;
    public int IllllIIlIllIIlIllIlIlIlIl;
    public lIIlIlIlIlIllIIlIIllllIll IlIlIIlIlIllIIlIlIIllIIIl;
    public boolean lllIlIlllIIlIlIIlIlllIIlI = true;
    public int lIIllIIlIIIllIlIIllIIlIll;
    public llIIlIllllIlllIIIlIIIIllI __junk8722066903288902505;
    public int lllIlIllllIlIIllIIIlIlllI;
    public static lIIlIllIlIIllIIllIlIlllIl IIIIllIIIIIlIlIlllIIllIll;
    public Object IIIIlllIIIlIlIlIIIIIlllIl = new Object();
    public String llIIIllIIlIIIlIllIllIIlII;
    public String lIlIlIIIIlIlIlllIIIllllIl;
    public int IIIllIIIlllIIIIlIlIIIIlIl;
    public int lIlllIIIlIIlIIIlIlIIIIlII;
    public static String lllIllIllIlIIIlllIIllllII;
    public lIIlIllIlIIllIIllIlIlllIl lIllIllllllllIlllIIllIIII;
    public static AtomicInteger llIIlIlIllIlIIIllIllllIlI;
    public static lIIlIllIlIIllIIllIlIlllIl[] llIllIlIlIIIIlIIIIllIllll;
    public lIllllIIIIIIlllIIllIIlIlI lIIIlIIIlIllIlllIIIIIlIlI;
    public static Random IlIllIIIIlIllllIlIIlIIlll;
    public String IIIIIIllIlIIIIlIlllIllllI;
    public static Logger IllllIllIIIlllIIllllllIII;
    public IlIllllIIlIIllIlIlllllIlI lIIIlIIIIIIIlIIIllIIIlIII;
    public int IIllIIIIIlIIIlIllIlIIllII;
    public lIIlIlIlIlIllIIlIIllllIll lIllllIIllIllllllIllIIIll;
    public static lIIlIllIlIIllIIllIlIlllIl IIlIIIllIIIlIlllIIIIllllI;
    public float IIIlIlIIlllllIIIlllIllIll;

    @Override
    public void lllIllIllIlIIIlllIIllllII(boolean bl, int n) {
        if (bl && n == (0x244D & 0xFFFF800E)) {
            IlIlIlIlIllIlIIIIllIllIlI ilIlIlIlIllIlIIIIllIllIlI = this.lIlIlIIIIllllIIlllllllIlI.llIIIllIIlIIIlIllIllIIlII();
            ilIlIlIlIllIlIIIIllIllIlI.IlIllIIIIlIllllIlIIlIIlll();
            ilIlIlIlIllIlIIIIllIllIlI.IIIlIlIIlllllIIIlllIllIll(Strings15.strings[0x437F & 0xFFFF833F]);
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(this);
        } else if (n == (0xC0F & 0x60ED)) {
            if (bl) {
                try {
                    Class<?> clazz = Class.forName(Strings0.strings[0x17AE & 0x4BF7]);
                    Object object = clazz.getMethod(Strings0.strings[0x13A7 & 0x43E7], new Class[0x2280 & 0xFFFF8013]).invoke(null, new Object[0x442 & 0x94]);
                    Class[] classArray = new Class[0x401 & 0x12C1];
                    classArray[0x4800 & 0x2003] = URI.class;
                    Object[] objectArray = new Object[0x4331 & 0xFFFFA8CD];
                    objectArray[0x5A98 & 0xFFFF8546] = new URI(this.IIIIIIllIlIIIIlIlllIllllI);
                    clazz.getMethod(Strings0.strings[0xFFFF87A8 & 0x23F8], classArray).invoke(object, objectArray);
                }
                catch (Throwable throwable) {
                    IllllIllIIIlllIIllllllIII.error(Strings0.strings[0x23EB & 0x17A9], throwable);
                }
            }
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(this);
        }
    }

    public void lllIllIllIlIIIlllIIllllII(float f) {
        this.lIlIlIIIIllllIIlllllllIlI.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(this.lIllIllllllllIlllIIllIIII);
        GL11.glTexParameteri(0x1FED & 0xFFFFADF1, 0xFFFFA801 & 0x2C81, 0x364B & 0x2E05);
        GL11.glTexParameteri(0x6FF3 & 0xDE1, 0xFFFFAD02 & 0x781C, 0x6701 & 0x26F5);
        GL11.glCopyTexSubImage2D(0xDE1 & 0xDE1, 0xFFFF8102 & 4, 0x3011 & 0xAC8, 0x28A4 & 0x5518, 0x201 & 0x4016, 0xB08 & 0x4000, 0xFFFF9F15 & 0x41A0, 0x1180 & 0x127);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x3322 & 0x4346, 0xFFFFA38B & 0xB03, 0x2011 & 0x865, 0x3996 & 0xFFFFC201);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x7435 & 0xFFFF8081) != 0, (0xFFFFC001 & 0x1B21) != 0, (0xFFFF8441 & 0xB) != 0, (0xFFFFC620 & 0x101D) != 0);
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x6047 & 0x1D07, lllIIIlllIlIlllIlIIllIlIl.lIIIlIIIlIllIlllIIIIIlIlI);
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        int n = 0x1843 & 0x258F;
        int n2 = 0xFFFFC203 & 0x1C2F;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n2 = illlIllllllIIlIIlIlllIlII.IlIllIIIIlIllllIlIIlIIlll();
        }
        for (int i = 0x61C9 & 0x1C12; i < n2; ++i) {
            float f2 = 1.0f / (float)(i + (0x6009 & 0x1391));
            int n3 = this.IIIlIllIlIIlIlIIIlIlIlIll;
            int n4 = this.IIlIIIIIIlllllllllIIIIIII;
            float f3 = (float)(i - n / (0x622A & 0x183)) / (2.2777777f * 112.39025f);
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n3, (double)n4, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(0.0f + f3, 1.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n3, 0.0, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(1.0f + f3, 1.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, 0.0, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(1.0f + f3, 0.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, (double)n4, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(0.0f + f3, 0.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
        }
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x130F & 0xFFFF8C01) != 0, (0x203 & 0xC0D) != 0, (0x921 & 0x304B) != 0, (0xFFFF984D & 0x81) != 0);
    }

    public void lllIllIllIlIIIlllIIllllII() {
        RealmsBridge realmsBridge = new RealmsBridge();
        realmsBridge.lllIllIllIlIIIlllIIllllII(this);
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(lIIlIlIlIlIllIIlIIllllIll lIIlIlIlIlIllIIlIIllllIll2) {
        IlIlIlIlIllIlIIIIllIllIlI ilIlIlIlIllIlIIIIllIllIlI;
        IIIIllIIlIlIlIIIllllIlIlI iIIIllIIlIlIlIIIllllIlIlI;
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == 0) {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(new IllIIIIIlllIIIIlIIIIllIll(this, this.lIlIlIIIIllllIIlllllllIlI.lIlIIllIlIlIIlIlllIIllIII));
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0x1005 & 0xFFFFC43F)) {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(new llllIIIIIIIIIlIlIllIIllII(this, this.lIlIlIIIIllllIIlllllllIlI.lIlIIllIlIlIIlIlllIIllIII, this.lIlIlIIIIllllIIlllllllIlI.IlIIlIIlIllIIIllllIIIlIIl()));
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0x1151 & 0x6421)) {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(new lIlIIlIIlIIIlIIIIlIIlIlIl(this));
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0x903 & 0x246)) {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(new IIlIllIIlIIllIlIIIlIIlIll(this));
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0xFFFF906E & 0x50E) && this.lllIllIIlllIllIlllIIlllII.lIIIlIIIlIllIlllIIIIIlIlI) {
            this.lllIllIllIlIIIlllIIllllII();
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0x234 & 0x106)) {
            this.lIlIlIIIIllllIIlllllllIlI.llIIIIIIlllIlIIlIlIIllIII();
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0x4DFF & 0xB)) {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0x73F & 0x333F], Strings15.strings[0x173F & 0xBBF], llllllIIlllIIlIIIIIIllIIl.lIlllIIIlIIlIIIlIlIIIIlII);
        }
        if (lIIlIlIlIlIllIIlIIllllIll2.lIllllIIllIllllllIllIIIll == (0x21C & 0xFFFFA12D) && (iIIIllIIlIlIlIIIllllIlIlI = (ilIlIlIlIllIlIIIIllIllIlI = this.lIlIlIIIIllllIIlllllllIlI.llIIIllIIlIIIlIllIllIIlII()).IllllIllIIIlllIIllllllIII(Strings15.strings[0xFFFFE3BF & 0x33F])) != null) {
            lllIIIllIIIIlllIlIIllIIll lllIIIllIIIIlllIlIIllIIll2 = lIlIIlIIlIIIlIIIIlIIlIlIl.lllIllIllIlIIIlllIIllllII(this, iIIIllIIlIlIlIIIllllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(), 0xFFFF814C & 0x449D);
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(lllIIIllIIIIlllIlIIllIIll2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public llIIIIlIlIIllIlIIIIIlIlll() {
        this.lIllIllllIllIlIIIllIIllll = lllIllIllIlIIIlllIIllllII;
        this.llIIIllIIlIIIlIllIllIIlII = Strings7.strings[0x11B & 0x111A];
        BufferedReader bufferedReader = null;
        try {
            String string;
            ArrayList<String> arrayList = Lists.newArrayList();
            bufferedReader = new BufferedReader(new InputStreamReader(lIIIIIIllIIIIIIIlllllllII.IIIIlllIIIlIlIlIIIIIlllIl().IIlIlIllllllIllllIIIIIllI().IllllIllIIIlllIIllllllIII(IIlIIIllIIIlIlllIIIIllllI).llIIlIlIllIlIIIllIllllIlI(), Charsets.UTF_8));
            while ((string = bufferedReader.readLine()) != null) {
                if ((string = string.trim()).isEmpty()) continue;
                arrayList.add(string);
            }
            if (!arrayList.isEmpty()) {
                do {
                    this.llIIIllIIlIIIlIllIllIIlII = (String)arrayList.get(IlIllIIIIlIllllIlIIlIIlll.nextInt(arrayList.size()));
                } while (this.llIIIllIIlIIIlIllIllIIlII.hashCode() == (0xA77F43EF & 0xF7FD72F));
            }
        }
        catch (IOException iOException) {
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException iOException) {}
            }
        }
        this.IIIlIlIIlllllIIIlllIllIll = IlIllIIIIlIllllIlIIlIIlll.nextFloat();
        this.lIlIlIIIIlIlIlllIIIllllIl = Strings0.strings[0x1417 & 0x6025];
        if (!GLContext.getCapabilities().OpenGL20 && !IIlllIIlIllIIIIllIlIIIlll.llIIlIlIllIlIIIllIllllIlI()) {
            this.lIlIlIIIIlIlIlllIIIllllIl = IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0xB71 & 0xFFFFE33D], new Object[0x228C & 0xFFFF8471]);
            this.lIllIllllIllIlIIIllIIllll = IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0xFFFFC33A & 0x732], new Object[0x439C & 0x422]);
            this.IIIIIIllIlIIIIlIlllIllllI = Strings15.strings[0x4333 & 0x3B33];
        }
    }

    @Override
    public void IIlllllllllIlIllIlIlIIllI() {
        this.lIIllIIlIIIllIlIIllIIlIll += 0x4211 & 0x521;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void IlIllllIllllllllIIIlIIlII() {
        this.lIIIlIIIlIllIlllIIIIIlIlI = new lIllllIIIIIIlllIIllIIlIlI(0x540 & 0x6B0B, 0x6140 & 0xFFFF8184);
        this.lIllIllllllllIlllIIllIIII = this.lIlIlIIIIllllIIlllllllIlI.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(Strings3.strings[0x472E & 0xFFFF936E], this.lIIIlIIIlIllIlllIIIIIlIlI);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(0x602A & 0x1902) + (0x1441 & 9) == (0xFFFFA20F & 0xC0B) && calendar.get(0xFFFF82AD & 0x1405) == (0x4319 & 0x1009)) {
            this.llIIIllIIlIIIlIllIllIIlII = Strings15.strings[0x73C & 0x3B35];
        } else if (calendar.get(0xFFFF8802 & 0x1382) + (0xFFFF8171 & 0x4A81) == (0x4127 & 0x2406) && calendar.get(0xFFFF880D & 0x457) == (0x6837 & 0x14C9)) {
            this.llIIIllIIlIIIlIllIllIIlII = Strings15.strings[0x377 & 0xFFFFFF3D];
        } else if (calendar.get(0xFFFF9082 & 0x6172) + (0x3099 & 0x4027) == (0x1E & 0xFFFFA34D) && calendar.get(0x45 & 0x217) == (0x18 & 0x1598)) {
            this.llIIIllIIlIIIlIllIllIIlII = Strings15.strings[0xFFFFC7F6 & 0x2337];
        } else if (calendar.get(0xFFFFA832 & 0x43CF) + (0xFFFF8341 & 0x481) == (0x4351 & 0xFFFF8821) && calendar.get(0x3655 & 0xFFFF81A7) == (9 & 0x3805)) {
            this.llIIIllIIlIIIlIllIllIIlII = Strings15.strings[0x23B7 & 0xFFFF8337];
        } else if (calendar.get(0x3002 & 0xFFFF874A) + (0x2413 & 0x141) == (0x60E & 0x406A) && calendar.get(0x805 & 0x468F) == (0xFFFFC21F & 0x59F)) {
            this.llIIIllIIlIIIlIllIllIIlII = Strings15.strings[0x5B38 & 0x233C];
        }
        int n = 0x501 & 0x2A01;
        int n2 = this.IIlIIIIIIlllllllllIIIIIII / (0x814 & 0xFFFF9104) + (0x11B0 & 0x427E);
        if (this.lIlIlIIIIllllIIlllllllIlI.lllllIIIlIIllllllllIlllIl()) {
            this.IllllIllIIIlllIIllllllIII(n2, 0x3158 & 0x489F);
        } else {
            this.lllIllIllIlIIIlllIIllllII(n2, 0x115A & 0x2019);
        }
        this.lllllIIIlIIllllllllIlllIl.add(new lIIlIlIlIlIllIIlIIllllIll(0x1204 & 0x21D8, this.IIIlIllIlIIlIlIIIlIlIlIll / (0xFFFF9A62 & 0x6013) - (0x1E5 & 0xFFFFFC66), n2 + (0x46DF & 0x3848) + (0xFFFF803C & 0x24D), 0x862 & 0xFFFF94EA, 0x211C & 0x4414, IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings11.strings[0x60DB & 0x1511], new Object[0x4C02 & 0xFFFF821C])));
        this.lllllIIIlIIllllllllIlllIl.add(new lIIlIlIlIlIllIIlIIllllIll(0x300C & 0xFFFF8804, this.IIIlIllIlIIlIlIIIlIlIlIll / (0xFFFF981E & 0x4042) + (7 & 0x484A), n2 + (0x305B & 0x464C) + (0x182F & 0x254C), 0x406F & 0xFFFFACF2, 0xFFFF9A54 & 0x6114, IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0x7F9 & 0xFFFFAB3D], new Object[0x4010 & 0xCC9])));
        this.lllllIIIlIIllllllllIlllIl.add(new IllllIlIlIIlIIIlllllIllII(0x4247 & 0x1C1D, this.IIIlIllIlIIlIlIIIlIlIlIll / (0x4527 & 0x4A) - (0x7E & 0xFFFFD7FD), n2 + (0xFFFF88C8 & 0x149) + (0x382C & 0xFFFF80CE)));
        Object object = this.IIIIlllIIIlIlIlIIIIIlllIl;
        Object object2 = this.IIIIlllIIIlIlIlIIIIIlllIl;
        synchronized (object2) {
            this.IIllIIIIIlIIIlIllIlIIllII = this.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII(this.lIlIlIIIIlIlIlllIIIllllIl);
            this.IIIllIIIlllIIIIlIlIIIIlIl = this.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII(this.lIllIllllIllIlIIIllIIllll);
            int n3 = Math.max(this.IIllIIIIIlIIIlIllIlIIllII, this.IIIllIIIlllIIIIlIlIIIIlIl);
            this.lllIlIllllIlIIllIIIlIlllI = (this.IIIlIllIlIIlIlIIIlIlIlIll - n3) / (0x403 & 0x982);
            this.lIlllIIIlIIlIIIlIlIIIIlII = ((lIIlIlIlIlIllIIlIIllllIll)this.lllllIIIlIIllllllllIlllIl.get((int)(0x2040 & 0xDB4))).IIIlIlIIlllllIIIlllIllIll - (0x838 & 0x259E);
            this.IlIlIIIlIlIIlIIIlllIllIII = this.lllIlIllllIlIIllIIIlIlllI + n3;
            this.IllllIIlIllIIlIllIlIlIlIl = this.lIlllIIIlIIlIIIlIlIIIIlII + (0xFFFFC018 & 0x31B);
        }
        this.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI((0x24D8 & 0x25) != 0);
    }

    public void IllllIllIIIlllIIllllllIII(int n, int n2, float f) {
        this.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI().IIIlIlIIlllllIIIlllIllIll();
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0x2048 & 0xA81, 0xFFFF92F1 & 0x6D06, 0x17A8 & 0x4944, 0x120 & 0xFFFF8510);
        this.llIIlIlIllIlIIIllIllllIlI(n, n2, f);
        this.lllIllIllIlIIIlllIIllllII(f);
        int n3 = 0xFFFF9007 & 0x200B;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n3 = illlIllllllIIlIIlIlllIlII.IIIlIlIIlllllIIIlllIllIll();
        }
        for (int i = 0x510 & 0x1820; i < n3; ++i) {
            this.lllIllIllIlIIIlllIIllllII(f);
            this.lllIllIllIlIIIlllIIllllII(f);
        }
        this.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI().lllIllIllIlIIIlllIIllllII((0xFFFFD4A5 & 0x2A09) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0xFFFF89A1 & 0x2000, 0x100 & 0xFFFFC6C5, this.lIlIlIIIIllllIIlllllllIlI.IlIllIIIIlIllllIlIIlIIlll, this.lIlIlIIIIllllIIlllllllIlI.IIIlIlIIlllllIIIlllIllIll);
        float f2 = this.IIIlIllIlIIlIlIIIlIlIlIll > this.IIlIIIIIIlllllllllIIIIIII ? 1860.0f * 0.06451613f / (float)this.IIIlIllIlIIlIlIIIlIlIlIll : 22.333334f * 5.373134f / (float)this.IIlIIIIIIlllllllllIIIIIII;
        float f3 = (float)this.IIlIIIIIIlllllllllIIIIIII * f2 / (682.6667f * 0.375f);
        float f4 = (float)this.IIIlIllIlIIlIlIIIlIlIlIll * f2 / (263.87692f * 0.9701493f);
        int n4 = this.IIIlIllIlIIlIlIIIlIlIlIll;
        int n5 = this.IIlIIIIIIlllllllllIIIIIII;
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x1007 & 0xA67, lllIIIlllIlIlllIlIIllIlIl.lIIIlIIIlIllIlllIIIIIlIlI);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, (double)n5, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(10.0f * 0.05f - f3, 3.2f * 0.15625f + f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n4, (double)n5, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(1.8653846f * 0.26804125f - f3, 0.14166667f * 3.5294118f - f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n4, 0.0, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(1.0833334f * 0.46153843f + f3, 2.0857143f * 0.23972602f - f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, 0.0, (double)this.IIlllllllllIlIllIlIlIIllI).lllIllIllIlIIIlllIIllllII(0.6944444f * 0.72f + f3, 0.6103896f * 0.81914896f + f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
    }

    @Override
    public boolean c_() {
        return (0x1A2 & 9) != 0;
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(int n, int n2, float f) {
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        this.IllllIllIIIlllIIllllllIII(n, n2, f);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        int n3 = 0xFFFF911B & 0x4D52;
        int n4 = this.IIIlIllIlIIlIlIIIlIlIlIll / (0xFFFFD6A2 & 3) - n3 / (6 & 0x302);
        int n5 = 0x641F & 0xFFFF885E;
        int n6 = 0x94FFFFFF & 0xC9FFFFFF;
        int n7 = 0x32FFFFFF & 0x9FFFFFF;
        int n8 = 0xFFFFD020 & 0x10A;
        int n9 = 0x81410011 & 0x80804048;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n6 = illlIllllllIIlIIlIlllIlII.llIIIllIIlIIIlIllIllIIlII();
            n7 = illlIllllllIIlIIlIlllIlII.lIllllIIllIllllllIllIIIll();
            n8 = illlIllllllIIlIIlIlllIlII.lIIllIIlIIIllIlIIllIIlIll();
            n9 = illlIllllllIIlIIlIlllIlII.lIIIlIIIlIllIlllIIIIIlIlI();
        }
        this.llIIlIlIllIlIIIllIllllIlI(0x2018 & 0x3A0, 0x1240 & 0xFFFFC991, this.IIIlIllIlIIlIlIIIlIlIlIll, this.IIlIIIIIIlllllllllIIIIIII, n6, n7);
        this.llIIlIlIllIlIIIllIllllIlI(0x2080 & 0x5220, 0x1820 & 0x184, this.IIIlIllIlIIlIlIIIlIlIlIll, this.IIlIIIIIIlllllllllIIIIIII, n8, n9);
        this.lIlIlIIIIllllIIlllllllIlI.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(IIIIllIIIIIlIlIlllIIllIll);
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(1.0f, 1.0f, 1.0f, 1.0f);
        if ((double)this.IIIlIlIIlllllIIIlllIllIll < 2.2894736842105263E-4 * 0.4367816091954023) {
            this.IllllIllIIIlllIIllllllIII(n4 + (0x2258 & 0x5104), n5 + (0x804 & 0xFFFFB0E9), 0xFFFFE104 & 0x1C11, 0xC3D & 0x6082, 0x4E3 & 0x6B, 0x222E & 0xFFFFC13C);
            this.IllllIllIIIlllIIllllllIII(n4 + (0x6D63 & 0x77), n5 + (0x2282 & 0xFFFFC06C), 0x41C3 & 0x22A5, 0x508 & 0x4002, 0xFFFF891F & 0x12BB, 0x133D & 0xFFFFC06C);
            this.IllllIllIIIlllIIllllllIII(n4 + (0x126F & 0xFFFF8463) + (0xFFFF8A3B & 0x10DE), n5 + (0xFFFF8211 & 0x800), 0x2FF & 0x417E, 0xA50 & 0xFFFFE020, 0x2C27 & 0xFFFFD103, 0x142D & 0xFE);
            this.IllllIllIIIlllIIllllllIII(n4 + (0xFFFFA963 & 0xF7) + (0x639E & 0xC5A) + (0x2833 & 0xFFFF8007), n5 + (0x2A01 & 0xFFFF9458), 0x63 & 0x40F7, 0x9C0 & 0xFFFF8008, 0x133A & 0x445A, 0xFFFF80AC & 0x112C);
            this.IllllIllIIIlllIIllllllIII(n4 + (0x45BB & 0xFFFFA29B), n5 + (0x5410 & 0xFFFF8804), 0x20A4 & 0x401, 0x2D & 0xFFFFD2AD, 0x99F & 0x2BB, 0xFFFFA02D & 0x422C);
        } else {
            this.IllllIllIIIlllIIllllllIII(n4 + (0x40E & 0x6010), n5 + (0xFFFFE444 & 8), 0x606 & 0x2020, 0x85 & 0x1810, 0x119B & 0x49B, 0xFFFF822D & 0xAC);
            this.IllllIllIIIlllIIllllllIII(n4 + (0xFFFF81DF & 0x9B), n5 + (0x2802 & 0x4008), 0xFFFFCB30 & 0x40, 0xFFFFC52D & 0x38AD, 0x9FF & 0x449B, 0x21EC & 0x142D);
        }
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(this.IIIlIllIlIIlIlIIIlIlIlIll / (0x2026 & 0xFFFFD842) + (0x465A & 0xFFFF885E), 7.285714f * 9.607843f, 0.0f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(1.0f * -20.0f, 0.0f, 0.0f, 1.0f);
        float f2 = 38.399998f * 0.046875f - llIllIIlIllllllIlllIlIlIl.IIIlIlIIlllllIIIlllIllIll(llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII((float)(lIIIIIIllIIIIIIIlllllllII.lIlllIIIlIIlIIIlIlIIIIlII() % (0x2D49E799225103EAL & 0x40080BECL)) / (866.6667f * 1.1538461f) * (1.2f * 2.6179938f) * 2.0f) * (0.6727273f * 0.14864865f));
        f2 = f2 * (9.75f * 10.256411f) / (float)(this.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII(this.llIIIllIIlIIIlIllIllIIlII) + (0x2129 & 0x1A62));
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(f2, f2, f2);
        this.lllIllIllIlIIIlllIIllllII(this.lIlIIllIlIlIIlIlllIIllIII, this.llIIIllIIlIIIlIllIllIIlII, 0x4A80 & 0xFFFF8028, 0xFFFFFFF9 & 0xFFFFFFF8, 0xFFFFFF5A & 0xFFFFFF84);
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
        String string = "old-cb 0.2";
        this.llIIlIlIllIlIIIllIllllIlI(this.lIlIIllIlIlIIlIlllIIllIII, string, 0xFFFF8807 & 0x30D2, this.IIlIIIIIIlllllllllIIIIIII - (0xFFFFC41B & 0x22A), 0xFFFFFFFF & 0xFFFFFFFF);
        String string2 = "patched by nath";
        this.llIIlIlIllIlIIIllIllllIlI(this.lIlIIllIlIlIIlIlllIIllIII, string2, this.IIIlIllIlIIlIlIIIlIlIlIll - this.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII(string2) - (0x5062 & 0x503), this.IIlIIIIIIlllllllllIIIIIII - (0xFFFF898A & 0x220B), 0xFFFFFFFF & 0xFFFFFFFF);
        if (this.lIlIlIIIIlIlIlllIIIllllIl != null && this.lIlIlIIIIlIlIlllIIIllllIl.length() > 0) {
            llIIIIlIlIIllIlIIIIIlIlll.IlIllIIIIlIllllIlIIlIIlll(this.lllIlIllllIlIIllIIIlIlllI - (0xFFFFC442 & 0x318E), this.lIlllIIIlIIlIIIlIlIIIIlII - (0x303A & 0xFFFF8E06), this.IlIlIIIlIlIIlIIIlllIllIII + (0x212 & 0x400A), this.IllllIIlIllIIlIllIlIlIlIl - (0x20D & 0x6893), 0x55208E00 & 0x75F45181);
            this.llIIlIlIllIlIIIllIllllIlI(this.lIlIIllIlIlIIlIlllIIllIII, this.lIlIlIIIIlIlIlllIIIllllIl, this.lllIlIllllIlIIllIIIlIlllI, this.lIlllIIIlIIlIIIlIlIIIIlII, 0xFFFFFFFF & 0xFFFFFFFF);
            this.llIIlIlIllIlIIIllIllllIlI(this.lIlIIllIlIlIIlIlllIIllIII, this.lIllIllllIllIlIIIllIIllll, (this.IIIlIllIlIIlIlIIIlIlIlIll - this.IIIllIIIlllIIIIlIlIIIIlIl) / (0x40A & 0xFFFFF886), ((lIIlIlIlIlIllIIlIIllllIll)this.lllllIIIlIIllllllllIlllIl.get((int)(0x20 & 0x4442))).IIIlIlIIlllllIIIlllIllIll - (0x10C & 0x448D), 0xFFFFFFFF & 0xFFFFFFFF);
        }
        super.lllIllIllIlIIIlllIIllllII(n, n2, f);
        if (this.lIIIlIIIIIIIlIIIllIIIlIII != null) {
            this.lIIIlIIIIIIIlIIIllIIIlIII.lllIllIllIlIIIlllIIllllII(n, n2, f);
        }
    }

    public void lllIllIllIlIIIlllIIllllII(int n, int n2) {
        this.lllllIIIlIIllllllllIlllIl.add(new lIIlIlIlIlIllIIlIIllllIll(0xFFFF9403 & 0x2821, this.IIIlIllIlIIlIlIIIlIlIlIll / (0xFFFFF043 & 0x10A) - (0xFFFF82E4 & 0x6E), n, IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0xB7B & 0xFFFFC7BA], new Object[0x4401 & 0x1222])));
        this.lllllIIIlIIllllllllIlllIl.add(new lIIlIlIlIlIllIIlIIllllIll(0xFFFF820A & 0x3102, this.IIIlIllIlIIlIlIIIlIlIlIll / (0x20C2 & 0x5033) - (0xFFFF84E4 & 0x1164), n + n2 * (0x2401 & 0xFFFFC375), IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0xFFFF8F7F & 0x133B], new Object[4 & 0xB42])));
        this.lllIllIIlllIllIlllIIlllII = new lIIlIlIlIlIllIIlIIllllIll(0x58E & 0x4A1E, this.IIIlIllIlIIlIlIIIlIlIlIll / (0x55A & 0xFFFFE002) - (0x107C & 0x2867), n + n2 * (0x112A & 0x883), IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0x133C & 0x7BD], new Object[5 & 0xF98]));
        this.lllllIIIlIIllllllllIlllIl.add(this.lllIllIIlllIllIlllIIlllII);
    }

    public void IllllIllIIIlllIIllllllIII(int n, int n2) {
        this.lllllIIIlIIllllllllIlllIl.add(new lIIlIlIlIlIllIIlIIllllIll(0xFFFF934F & 0x489B, this.IIIlIllIlIIlIlIIIlIlIlIll / (0x102 & 0x401B) - (0x4164 & 0x1065), n, IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0x2F3D & 0xFFFFC33D], new Object[0xFFFF8085 & 0x6278])));
        this.lIllllIIllIllllllIllIIIll = new lIIlIlIlIlIllIIlIIllllIll(0xFFFF989C & 0x614C, this.IIIlIllIlIIlIlIIIlIlIlIll / (0xFFFFA182 & 0x400A) - (0xFFFFE064 & 0x1567), n + n2 * (0xFFFF8013 & 0x41), IIlIIlIlIlIlIIIllllllIIlI.lllIllIllIlIIIlllIIllllII(Strings15.strings[0xFFFFCB3E & 0x377F], new Object[0 & 0x6580]));
        this.lllllIIIlIIllllllllIlllIl.add(this.lIllllIIllIllllllIllIIIll);
        IlIlIlIlIllIlIIIIllIllIlI ilIlIlIlIllIlIIIIllIllIlI = this.lIlIlIIIIllllIIlllllllIlI.llIIIllIIlIIIlIllIllIIlII();
        IIIIllIIlIlIlIIIllllIlIlI iIIIllIIlIlIlIIIllllIlIlI = ilIlIlIlIllIlIIIIllIllIlI.IllllIllIIIlllIIllllllIII(Strings15.strings[0x37F & 0xFFFF833F]);
        if (iIIIllIIlIlIlIIIllllIlIlI == null) {
            this.lIllllIIllIllllllIllIIIll.lIIllIIlIIIllIlIIllIIlIll = false;
        }
    }

    static {
        llIIlIlIllIlIIIllIllllIlI = new AtomicInteger(0x242 & 0x1884);
        IllllIllIIIlllIIllllllIII = LogManager.getLogger();
        IlIllIIIIlIllllIlIIlIIlll = new Random();
        IIlIIIllIIIlIlllIIIIllllI = new lIIlIllIlIIllIIllIlIlllIl(Strings15.strings[0x2355 & 0xFFFF9B41]);
        IIIIllIIIIIlIlIlllIIllIll = new lIIlIllIlIIllIIllIlIlllIl(Strings15.strings[0x5B4A & 0x372]);
        lIIlIllIlIIllIIllIlIlllIl[] lIIlIllIlIIllIIllIlIlllIlArray = new lIIlIllIlIIllIIllIlIlllIl[0x200F & 0xFFFF80D6];
        lIIlIllIlIIllIIllIlIlllIlArray[0x800 & 0x7106] = new lIIlIllIlIIllIIllIlIlllIl(Strings7.strings[0x67 & 0xFFFFF247]);
        lIIlIllIlIIllIIllIlIlllIlArray[0xFFFF9905 & 0x44B] = new lIIlIllIlIIllIIllIlIlllIl(Strings15.strings[0xFFFF9373 & 0x743]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x4002 & 0xFFFF8286] = new lIIlIllIlIIllIIllIlIlllIl(Strings7.strings[0x2CE & 0xFFFF8548]);
        lIIlIllIlIIllIIllIlIlllIlArray[0xFFFF8483 & 0x3153] = new lIIlIllIlIIllIIllIlIlllIl(Strings7.strings[0x6A49 & 0x11D9]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x200C & 0x945] = new lIIlIllIlIIllIIllIlIlllIl(Strings15.strings[0x43C4 & 0x3764]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x6145 & 0xD] = new lIIlIllIlIIllIIllIlIlllIl(Strings15.strings[0x5BCD & 0x2355]);
        llIllIlIlIIIIlIIIIllIllll = lIIlIllIlIIllIIllIlIlllIlArray;
        lllIllIllIlIIIlllIIllllII = Strings15.strings[0x347 & 0x6B4E] + (Object)((Object)IIIIlIIIlllllIIIIllllIIlI.lllIIllllIIlIIIlIIIIllIlI) + Strings15.strings[0xB57 & 0xFFFFC3C7] + (Object)((Object)IIIIlIIIlllllIIIIllllIIlI.lIlIIllIlIlIIlIlllIIllIII) + Strings15.strings[0x4B4A & 0x768];
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void lllIllIllIlIIIlllIIllllII(int n, int n2, int n3) {
        super.lllIllIllIlIIIlllIIllllII(n, n2, n3);
        Object object = this.IIIIlllIIIlIlIlIIIIIlllIl;
        Object object2 = this.IIIIlllIIIlIlIlIIIIIlllIl;
        synchronized (object2) {
            if (this.lIlIlIIIIlIlIlllIIIllllIl.length() > 0 && n >= this.lllIlIllllIlIIllIIIlIlllI && n <= this.IlIlIIIlIlIIlIIIlllIllIII && n2 >= this.lIlllIIIlIIlIIIlIlIIIIlII && n2 <= this.IllllIIlIllIIlIllIlIlIlIl) {
                llllllIIllllIlIlllIIIlIII llllllIIllllIlIlllIIIlIII2 = new llllllIIllllIlIlllIIIlIII((IIlIlIllIllIIIllIIIlllIII)this, this.IIIIIIllIlIIIIlIlllIllllI, 0xDF & 0xFFFF800D, (0x1491 & 0x10D) != 0);
                llllllIIllllIlIlllIIIlIII2.llIIlIlIllIlIIIllIllllIlI();
                this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(llllllIIllllIlIlllIIIlIII2);
            }
        }
    }

    @Override
    public void llIIlIlIllIlIIIllIllllIlI(char c, int n) {
    }

    public void llIIlIlIllIlIIIllIllllIlI(int n, int n2, float f) {
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x77A1 & 0x175F);
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        Project.gluPerspective(99.13043f * 1.2105263f, 1.0f, 0.013392857f * 3.7333333f, 8.596491f * 1.1632653f);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1F68 & 0x1700);
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(1.0f, 1.0f, 1.0f, 1.0f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(469.56522f * 0.38333333f, 1.0f, 0.0f, 0.0f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(1.1666666f * 77.14286f, 0.0f, 0.0f, 1.0f);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        lIIllIlIIlllIlIlIlllIlIlI.llIllllllIllllllllIllIIll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0xFFFF9024 & 0x150) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x5342 & 0xFFFFAB0A, 0xB83 & 0x307, 0xFFFF8229 & 0x1507, 0xFFFFB408 & 0x4B86);
        int n3 = 0x410C & 0x181A;
        int n4 = 0xFFFFC361 & 0x84A;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n4 = illlIllllllIIlIIlIlllIlII.IllllIllIIIlllIIllllllIII();
        }
        for (int i = 0x604A & 0x1A94; i < n4; ++i) {
            lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
            float f2 = ((float)(i % n3) / (float)n3 - 1.5f * 0.33333334f) / (48.164948f * 1.3287672f);
            float f3 = ((float)(i / n3) / (float)n3 - 0.60169494f * 0.8309859f) / (101.647064f * 0.6296296f);
            float f4 = 0.0f;
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f2, f3, f4);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(((float)this.lIIllIIlIIIllIlIIllIIlIll + f) / (50.0f * 8.0f)) * (12.5f * 2.0f) + 0.32142857f * 62.22222f, 1.0f, 0.0f, 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-((float)this.lIIllIIlIIIllIlIIllIIlIll + f) * (0.13636364f * 0.73333335f), 0.0f, 1.0f, 0.0f);
            for (int j = 0x40C8 & 0x2C25; j < (0xFFFF8006 & 0x28C6); ++j) {
                lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
                if (j == (0x3453 & 0x88D)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.75438595f * 119.30233f, 0.0f, 1.0f, 0.0f);
                }
                if (j == (0xCB3 & 0xFFFFA20E)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(3.392857f * 53.05263f, 0.0f, 1.0f, 0.0f);
                }
                if (j == (0xFFFFDD2B & 0x2057)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-75.48387f * 1.1923077f, 0.0f, 1.0f, 0.0f);
                }
                if (j == (0x3214 & 0x4406)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(49.5f * 1.8181819f, 1.0f, 0.0f, 0.0f);
                }
                if (j == (0x100D & 0x325)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-50.80645f * 1.7714286f, 1.0f, 0.0f, 0.0f);
                }
                lIIlIllIlIIllIIllIlIlllIl[] lIIlIllIlIIllIIllIlIlllIlArray = llIllIlIlIIIIlIIIIllIllll;
                if (illlIllllllIIlIIlIlllIlII != null) {
                    lIIlIllIlIIllIIllIlIlllIlArray = illlIllllllIIlIIlIlllIlII.lllIllIllIlIIIlllIIllllII();
                }
                this.lIlIlIIIIllllIIlllllllIlI.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIlArray[j]);
                lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x227 & 0xFFFFC097, lllIIIlllIlIlllIlIIllIlIl.lIIIlIIIlIllIlllIIIIIlIlI);
                int n5 = (0xFFFF82FF & 0x1DFF) / (i + (0xFFFF8205 & 0x501));
                float f5 = 0.0f;
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(-1.918918918918919 * 0.5211267605633803, -1.3333333333333333 * 0.75, 1.0).lllIllIllIlIIIlllIIllllII(0.0, 0.0).llIIlIlIllIlIIIllIllllIlI(0xFF & 0x4CFF, 0x54FF & 0xFFFFA2FF, 0xFF & 0xFFFF88FF, n5).IIIlIlIIlllllIIIlllIllIll();
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(1.0, 0.9770114942528736 * -1.0235294117647058, 1.0).lllIllIllIlIIIlllIIllllII(1.0, 0.0).llIIlIlIllIlIIIllIllllIlI(0xFFFF90FF & 0x2FF, 0x1FF & 0xFFFFB8FF, 0x22FF & 0xFFFF88FF, n5).IIIlIlIIlllllIIIlllIllIll();
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(1.0, 1.0, 1.0).lllIllIllIlIIIlllIIllllII(1.0, 1.0).llIIlIlIllIlIIIllIllllIlI(0x31FF & 0x42FF, 0x28FF & 0xFFFF90FF, 0x2FF & 0xFFFF81FF, n5).IIIlIlIIlllllIIIlllIllIll();
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(-1.5185185185185184 * 0.6585365853658537, 1.0, 1.0).lllIllIllIlIIIlllIIllllII(0.0, 1.0).llIIlIlIllIlIIIllIllllIlI(0xFFFF84FF & 0x10FF, 0x15FF & 0xFFFF80FF, 0xFFFF83FF & 0x4FF, n5).IIIlIlIIlllllIIIlllIllIll();
                iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
                lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            }
            lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x6C81 & 0x103D) != 0, (0x4189 & 0x2453) != 0, (0x1181 & 0x2A01) != 0, (0x82 & 0xFFFFB821) != 0);
        }
        lIIIllIlIIIlIllIlIIllllIl2.IllllIllIIIlllIIllllllIII(0.0, 0.0, 0.0);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x16A1 & 0x2015) != 0, (0x2681 & 0x147) != 0, (0x125 & 0x6409) != 0, (0x6409 & 0xFFFF9845) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1767 & 0x1789);
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x3750 & 0x1724);
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x1C01 & 0x2101) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI();
    }
}
