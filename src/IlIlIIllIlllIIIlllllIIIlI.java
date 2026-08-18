/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : le vrai écran menu principal cheatbreaker (llllIIIlllIIIllIlllIIlIlI,
 * celui qui remplace vraiment le GuiMainMenu vanilla au boot cf le swap dans
 * lIIIllIIIIIlIllIlllIlllII) hérite tout son rendu de bas d'écran d'ici --
 * llIIIIlIlIIllIlIIIIIlIlll (le vanilla) a le même genre de texte mais
 * n'est jamais affiché en vrai vu qu'il est remplacé avant le premier rendu
 * donc c'est ici qu'il fallait patcher les deux textes du bas pas là-bas
 * (laissé tel quel là-bas c'est inoffensif mais mort)
 */
import com.google.common.collect.ImmutableList;
import generated.Strings0;
import generated.Strings1;
import generated.Strings3;
import generated.Strings9;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

public class IlIlIIllIlllIIIlllllIIIlI
extends lIIIlIIIllIllIIIlllIlllII {
    public IllIllllIIIlllllllIIIIIIl IllllIllIIIlllIIllllllIII;
    public double IIIIIIllIlIIIIlIlllIllllI;
    public IllIllllIIIlllllllIIIIIIl lllIlIlllIIlIlIIlIlllIIlI;
    public lIIlIllIlIIllIIllIlIlllIl lIllIllllIllIlIIIllIIllll;
    public IllIllllIIIlllllllIIIIIIl IlIllIIIIlIllllIlIIlIIlll;
    public static int IIIIlllIIIlIlIlIIIIIlllIl = 0xFFFFD0DC & 0x22;
    public IllIllllIIIlllllllIIIIIIl llIIlIlIllIlIIIllIllllIlI;
    public lIIlIllIlIIllIIllIlIlllIl[] lIlIlIIIIlIlIlllIIIllllIl;
    public IlllllIlIIIlIIlIIllIIlIll lllIllIllIlIIIlllIIllllII;

    @Override
    public void lllIllIllIlIIIlllIIllllII(int n, int n2, float f) {
        if (IlIIllIIllIIIllllIIllIIII.lIllllIIllIllllllIllIIIll() == null) {
            lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
            this.llIIlIlIllIlIIIllIllllIlI(n, n2, f);
            lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        }
        super.lllIllIllIlIIIlllIIllllII(n, n2, f);
    }

    public void lllIllIllIlIIIlllIIllllII(float f) {
        this.lIlIlIIIIllllIIlllllllIlI.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(this.lIllIllllIllIlIIIllIIllll);
        GL11.glTexParameteri(0xDE9 & 0xFE1, 0x2E83 & 0x2849, 0x2601 & 0x2E53);
        GL11.glTexParameteri(0xDE1 & 0x1DF5, 0x3C80 & 0xFFFFA803, 0x2745 & 0xFFFFB689);
        GL11.glCopyTexSubImage2D(0x5FE3 & 0xFFFF8DE1, 0xB26 & 0xFFFFD081, 0xFFFF95D6 & 0x2208, 0xFFFFA121 & 0x485A, 0x6102 & 0xFFFF8801, 0x418 & 0xFFFF8084, 0xFFFF8928 & 0x2301, 0xFFFF8502 & 0x4188);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x2302 & 0x7F6, 0x4FCB & 0x303, 0x281 & 0x453D, 0x40B0 & 0xFFFF9A00);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x41 & 0x2125) != 0, (0x624D & 0xFFFF8413) != 0, (0xFFFFE8C3 & 0x321) != 0, (0x1000 & 0xFFFF8238) != 0);
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0xFFFF8087 & 7, lllIIIlllIlIlllIlIIllIlIl.lIIIlIIIlIllIlllIIIIIlIlI);
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        int n = 0xFFFF8463 & 0x1F;
        int n2 = 0xFFFF8627 & 0x4003;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n2 = illlIllllllIIlIIlIlllIlII.IlIllIIIIlIllllIlIIlIIlll();
        }
        for (int i = 0x2004 & 0x180; i < n2; ++i) {
            float f2 = 1.0f / (float)(i + (0x4805 & 0x451));
            int n3 = this.IIIlIllIlIIlIlIIIlIlIlIll;
            int n4 = this.IIlIIIIIIlllllllllIIIIIII;
            float f3 = (float)(i - n / (0x214B & 0x5002)) / (0.57731956f * 443.4286f);
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n3, (double)n4, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(0.0f + f3, 1.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n3, 0.0, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(1.0f + f3, 1.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, 0.0, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(1.0f + f3, 0.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
            lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, (double)n4, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(0.0f + f3, 0.0).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, f2).IIIlIlIIlllllIIIlllIllIll();
        }
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x3403 & 0xAC1) != 0, (0xFFFFA183 & 0x1001) != 0, (0xFFFFD01F & 0xC61) != 0, (0x204D & 0x4013) != 0);
    }

    @Override
    public void IllllIllIIIlllIIllllllIII() {
    }

    public void IllllIllIIIlllIIllllllIII(int n, int n2, float f) {
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x17C5 & 0xFFFFDF11);
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        Project.gluPerspective(4.611111f * 26.024096f, 1.0f, 2.25f * 0.022222223f, 2.4675326f * 4.0526314f);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x1F10 & 0xFFFFF700);
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        lIIllIlIIlllIlIlIlllIlIlI.IIIIllIIIIIlIlIlllIIllIll();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(1.0f, 1.0f, 1.0f, 1.0f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0625f * 2880.0f, 1.0f, 0.0f, 0.0f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(16.5f * 5.4545455f, 0.0f, 0.0f, 1.0f);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        lIIllIlIIlllIlIlIlllIlIlI.llIllllllIllllllllIllIIll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0xFFFF83A0 & 0x204F) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0x4756 & 0x323, 0xFFFFA3B3 & 0x70B, 7 & 0x1D89, 0x26B & 0xFFFFA980);
        int n3 = 0x1028 & 0xFFFF8F4C;
        int n4 = 0x21E4 & 0x4042;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n4 = illlIllllllIIlIIlIlllIlII.IllllIllIIIlllIIllllllIII();
        }
        for (int i = 0xFFFF8828 & 0x3501; i < n4; ++i) {
            lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
            float f2 = ((float)(i % n3) / (float)n3 - 1.7021277f * 0.29375f) / (0.125f * 512.0f);
            float f3 = ((float)(i / n3) / (float)n3 - 0.39759037f * 1.2575758f) / (1.7297298f * 37.0f);
            float f4 = 0.0f;
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(f2, f3, f4);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(llIllIIlIllllllIlllIlIlIl.lllIllIllIlIIIlllIIllllII(((float)IIIIlllIIIlIlIlIIIIIlllIl + f) / (2.6f * 153.84616f)) * (1.5882353f * 15.740741f) + 2.2222223f * 9.0f, 1.0f, 0.0f, 0.0f);
            lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-((float)IIIIlllIIIlIlIlIIIIIlllIl + f) * (0.14516129f * 0.6888889f), 0.0f, 1.0f, 0.0f);
            for (int j = 0x201 & 0xFFFFB024; j < (0xFFFF988E & 0x246); ++j) {
                lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
                if (j == (0x10A5 & 0x6513)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(64.067795f * 1.4047619f, 0.0f, 1.0f, 0.0f);
                }
                if (j == (0x3D8A & 0x26)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.6630435f * 271.4754f, 0.0f, 1.0f, 0.0f);
                }
                if (j == (0xB43 & 0x100B)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.16129032f * -558.0f, 0.0f, 1.0f, 0.0f);
                }
                if (j == (0x168D & 0x4014)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(1.0144928f * 88.71429f, 1.0f, 0.0f, 0.0f);
                }
                if (j == (0x1F & 0x445)) {
                    lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(-41.785713f * 2.1538463f, 1.0f, 0.0f, 0.0f);
                }
                lIIlIllIlIIllIIllIlIlllIl[] lIIlIllIlIIllIIllIlIlllIlArray = this.lIlIlIIIIlIlIlllIIIllllIl;
                if (illlIllllllIIlIIlIlllIlII != null) {
                    lIIlIllIlIIllIIllIlIlllIlArray = illlIllllllIIlIIlIlllIlII.lllIllIllIlIIIlllIIllllII();
                }
                this.lIlIlIIIIllllIIlllllllIlI.lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIlArray[j]);
                lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x807 & 0x57, lllIIIlllIlIlllIlIIllIlIl.lIIIlIIIlIllIlllIIIIIlIlI);
                int n5 = (0xFFFF88FF & 0x2FF) / (i + (0x4003 & 0xFFFF98A9));
                float f5 = 0.0f;
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.8585858585858586 * -1.1647058823529413, -0.17857142857142858 * 5.6, 1.0).lllIllIllIlIIIlllIIllllII(0.0, 0.0).llIIlIlIllIlIIIllIllllIlI(0x1FF & 0x68FF, 0x22FF & 0x54FF, 0xFFFFC9FF & 0xFF, n5).IIIlIlIIlllllIIIlllIllIll();
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(1.0, -2.75 * 0.36363636363636365, 1.0).lllIllIllIlIIIlllIIllllII(1.0, 0.0).llIIlIlIllIlIIIllIllllIlI(0xCFF & 0x20FF, 0x57FF & 0x8FF, 0xFF & 0x3FF, n5).IIIlIlIIlllllIIIlllIllIll();
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(1.0, 1.0, 1.0).lllIllIllIlIIIlllIIllllII(1.0, 1.0).llIIlIlIllIlIIIllIllllIlI(0xFFFF82FF & 0x1FF, 0xFF & 0xFFFFE2FF, 0x10FF & 0x48FF, n5).IIIlIlIIlllllIIIlllIllIll();
                lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(1.2753623188405796 * -0.7840909090909092, 1.0, 1.0).lllIllIllIlIIIlllIIllllII(0.0, 1.0).llIIlIlIllIlIIIllIllllIlI(0xFFFF89FF & 0x20FF, 0x30FF & 0x44FF, 0x40FF & 0x20FF, n5).IIIlIlIIlllllIIIlllIllIll();
                iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
                lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            }
            lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
            lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x1441 & 0xFFFF82B3) != 0, (0xFFFFC001 & 0x3A01) != 0, (0x4A2B & 0x481) != 0, (0x4110 & 0x2A0) != 0);
        }
        lIIIllIlIIIlIllIlIIllllIl2.IllllIllIIIlllIIllllllIII(0.0, 0.0, 0.0);
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x353 & 0x5089) != 0, (0xFFFFC145 & 0x821) != 0, (0x20F1 & 0xFFFFCB09) != 0, (0x2061 & 0xFFFF8E95) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x7721 & 0x1751);
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI(0x371C & 0xFFFFDF82);
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII((0x5AD3 & 0xFFFFA001) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.lIlIlIIIIllllIIlllllllIlI();
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI();
    }

    public void IIIlIlIIlllllIIIlllIllIll() {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(0.0f, 0.0f, this.IIlIIIIIIlllllllllIIIIIII(), this.lllllIIIlIIllllllllIlllIl(), 0x767E3C34 & 0x69B536F5);
    }

    @Override
    public List lIllllIIllIllllllIllIIIll() {
        this.lllIllIllIlIIIlllIIllllII = new IlllllIlIIIlIIlIIllIIlIll(null);
        this.lllIlIlllIIlIlIIlIlllIIlI = new IllIllllIIIlllllllIIIIIIl(null, new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x51F4 & 0xFFFFA9F1]));
        this.llIIlIlIllIlIIIllIllllIlI = new IllIllllIIIlllllllIIIIIIl(null, new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x41F1 & 0xFFFF8BFB]));
        this.IllllIllIIIlllIIllllllIII = new IllIllllIIIlllllllIIIIIIl(null, new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0xFFFF83FE & 0x1F2]));
        this.IlIllIIIIlIllllIlIIlIIlll = new IllIllllIIIlllllllIIIIIIl(null, new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x13F3 & 0x5F3]));
        return ImmutableList.of(this.lllIllIllIlIIIlllIIllllII, this.lllIlIlllIIlIlIIlIlllIIlI, this.llIIlIlIllIlIIIllIllllIlI, this.IllllIllIIIlllIIllllllIII, this.IlIllIIIIlIllllIlIIlIIlll);
    }

    @Override
    public void lIIllIIlIIIllIlIIllIIlIll() {
        IIIIlllIIIlIlIlIIIIIlllIl += 0x6C1 & 0x111;
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII() {
    }

    @Override
    public void llIIlIlIllIlIIIllIllllIlI(float f, float f2, int n) {
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(float f, float f2) {
        this.IIIlIlIIlllllIIIlllIllIll();
        this.IlIllIIIIlIllllIlIIlIIlll();
    }

    public void llIIlIlIllIlIIIllIllllIlI(int n, int n2, float f) {
        this.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI().IIIlIlIIlllllIIIlllIllIll();
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0xFFFFF478 & 0xA03, 0xE00 & 0xFFFF80D0, 0x5B01 & 0xFFFF8140, 0x189 & 0x1300);
        this.IllllIllIIIlllIIllllllIII(n, n2, f);
        this.lllIllIllIlIIIlllIIllllII(f);
        int n3 = 0xFFFFC063 & 0x2003;
        IlllIllllllIIlIIlIlllIlII illlIllllllIIlIIlIlllIlII = lIllIlllllIlIlIIllIIIllII.lllIllIllIlIIIlllIIllllII();
        if (illlIllllllIIlIIlIlllIlII != null) {
            n3 = illlIllllllIIlIIlIlllIlII.IIIlIlIIlllllIIIlllIllIll();
        }
        for (int i = 0xFFFFB007 & 0xB98; i < n3; ++i) {
            this.lllIllIllIlIIIlllIIllllII(f);
            this.lllIllIllIlIIIlllIIllllII(f);
        }
        this.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI().lllIllIllIlIIIlllIIllllII((0x2009 & 0x1007) != 0);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0x4A40 & 0xFFFF80B9, 0x1578 & 2, this.lIlIlIIIIllllIIlllllllIlI.IlIllIIIIlIllllIlIIlIIlll, this.lIlIlIIIIllllIIlllllllIlI.IIIlIlIIlllllIIIlllIllIll);
        float f2 = this.IIIlIllIlIIlIlIIIlIlIlIll > this.IIlIIIIIIlllllllllIIIIIII ? 0.34f * 352.94116f / (float)this.IIIlIllIlIIlIlIIIlIlIlIll : 246.3158f * 0.4871795f / (float)this.IIlIIIIIIlllllllllIIIIIII;
        float f3 = (float)this.IIlIIIIIIlllllllllIIIIIII * f2 / (983.04004f * 0.26041666f);
        float f4 = (float)this.IIIlIllIlIIlIlIIIlIlIlIll * f2 / (388.26666f * 0.6593407f);
        int n4 = this.IIIlIllIlIIlIlIIIlIlIlIll;
        int n5 = this.IIlIIIIIIlllllllllIIIIIII;
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0xFFFFC017 & 0x227, lllIIIlllIlIlllIlIIllIlIl.lIIIlIIIlIllIlllIIIIIlIlI);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, (double)n5, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(0.44339624f * 1.1276596f - f3, 0.5365854f * 0.9318182f + f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n4, (double)n5, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(0.111940295f * 4.4666667f - f3, 0.20652173f * 2.4210527f - f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)n4, 0.0, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(0.6262626f * 0.7983871f + f3, 0.08695652f * 5.75f - f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI(0.0, 0.0, this.IIIIIIllIlIIIIlIlllIllllI).lllIllIllIlIIIlllIIllllII(1.4561404f * 0.34337348f + f3, 0.8f * 0.625f + f4).lllIllIllIlIIIlllIIllllII(1.0f, 1.0f, 1.0f, 1.0f).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(char c, int n) {
    }

    @Override
    public void llIIlIlIllIlIIIllIllllIlI() {
        float f = 6.725275f * 2.6764705f;
        this.lllIlIlllIIlIlIIlIlllIIlI.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() - f - 4.0f * 2.0f, 0.375f * 26.666666f, f, f);
        this.lllIllIllIlIIIlllIIllllII.lllIllIllIlIIIlllIIllllII(f - f / 2.0f, 0.32258064f * 31.0f, f, f);
        this.llIIlIlIllIlIIIllIllllIlI.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - f * 2.0f + 10.206897f * 0.3918919f, this.lllllIIIlIIllllllllIlllIl() - f - 0.26262626f * 45.692307f, f, f);
        this.IllllIllIIIlllIIllllllIII.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - f / 2.0f, this.lllllIIIlIIllllllllIlllIl() - f - 13.629629f * 0.8804348f, f, f);
        this.IlIllIIIIlIllllIlIIlIIlll.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f + f / 2.0f + 2.8333333f * 1.4117647f, this.lllllIIIlIIllllllllIlllIl() - f - 12.685715f * 0.9459459f, f, f);
    }

    public IlIlIIllIlllIIIlllllIIIlI() {
        lIIlIllIlIIllIIllIlIlllIl[] lIIlIllIlIIllIIllIlIlllIlArray = new lIIlIllIlIIllIIllIlIlllIl[0x3906 & 0xFFFFC006];
        lIIlIllIlIIllIIllIlIlllIlArray[0xFFFF8029 & 0x2A44] = new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x3EA & 0xDEA]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x4345 & 0x14B1] = new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x3EF & 0x21FB]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x222 & 0x6847] = new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x11EC & 0x9FD]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x113 & 0xFFFFAE4B] = new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0xFFFF81ED & 0x41ED]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x407C & 0xFFFF8C06] = new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0x59EE & 0x3FF]);
        lIIlIllIlIIllIIllIlIlllIlArray[0x205 & 0x25] = new lIIlIllIlIIllIIllIlIlllIl(Strings9.strings[0xFFFF81EF & 0x5BFF]);
        this.lIlIlIIIIlIlIlllIIIllllIl = lIIlIllIlIIllIIllIlIlllIlArray;
        this.IIIIIIllIlIIIIlIlllIllllI = 0.0;
        lIllllIIIIIIlllIIllIIlIlI lIllllIIIIIIlllIIllIIlIlI2 = new lIllllIIIIIIlllIIllIIlIlI(0xFFFFAD0B & 0x344, 0x1B01 & 0x616C);
        this.lIllIllllIllIlIIIllIIllll = IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lIIIlIIIIIIIlIIIllIIIlIII().lllIllIllIlIIIlllIIllllII(Strings3.strings[0x43AF & 0xFFFFA33E], lIllllIIIIIIlllIIllIIlIlI2);
        this.llIIlIlIllIlIIIllIllllIlI.lllIllIllIlIIIlllIIllllII((float f, float f2, int n) -> {
            IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII(new lIllIllIllllllIllIlllIlIl());
            return (0xFFFF8901 & 0x3283) != 0;
        });
        this.IllllIllIIIlllIIllllllIII.lllIllIllIlIIIlllIIllllII((float f, float f2, int n) -> {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(new IllIIIIIlllIIIIlIIIIllIll(this, this.lIlIlIIIIllllIIlllllllIlI.lIlIIllIlIlIIlIlllIIllIII));
            return (0x2009 & 0x5203) != 0;
        });
        this.IlIllIIIIlIllllIlIIlIIlll.lllIllIllIlIIIlllIIllllII((float f, float f2, int n) -> {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII(new llllIIIIIIIIIlIlIllIIllII(this, this.lIlIlIIIIllllIIlllllllIlI.lIlIIllIlIlIIlIlllIIllIII, this.lIlIlIIIIllllIIlllllllIlI.IlIIlIIlIllIIIllllIIIlIIl()));
            return (0x429 & 0x4291) != 0;
        });
        this.lllIlIlllIIlIlIIlIlllIIlI.lllIllIllIlIIIlllIIllllII((float f, float f2, int n) -> {
            this.lIlIlIIIIllllIIlllllllIlI.lIllllIIllIllllllIllIIIll();
            return (0xFFFF8109 & 0x14E1) != 0;
        });
        this.lllIlIlllIIlIlIIlIlllIIlI.llIIlIlIllIlIIIllIllllIlI(new lIlllllIIlllIIIlllllllIII(0x24FFFFFF & 0x20FFFFFF, 0xD5FF3638 & 0x52FF3035));
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(float f, float f2, int n) {
    }

    public void IlIllIIIIlIllllIlIIlIIlll() {
        String string = "old-cb 0.2";
        IIlIIllIllIlIIlIlIIlIIIII.lllIllIllIlIIIlllIIllllII.lllIllIllIlIIIlllIIllllII(string, 1.78f * 5.6179776f, this.lllllIIIlIIllllllllIlllIl() - 1.0f * 16.0f, 0xA4CDCFCD & 0xA1DDCDCD);
        String string2 = "patched by nath";
        int width2 = IIlIIllIllIlIIlIlIIlIIIII.lllIllIllIlIIIlllIIllllII.llIIlIlIllIlIIIllIllllIlI(string2);
        IIlIIllIllIlIIlIlIIlIIIII.lllIllIllIlIIIlllIIllllII.lllIllIllIlIIIlllIIllllII(string2, this.IIlIIIIIIlllllllllIIIIIII() - (float)width2 - 1.78f * 5.6179776f, this.lllllIIIlIIllllllllIlllIl() - 2.5f * 6.4f, 0xA1CFCDCD & 0xE2CDFFFD);
    }
}
