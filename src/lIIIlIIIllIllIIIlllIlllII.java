/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : classe de base d'origine partagée par plusieurs modules hud (ceux
 * avec une option "background") qui dessine un fond flouté derrière via
 * mc.entityRenderer.loadShader(shaders/post/menu_blur.json) -- cet asset
 * shader existe pas vraiment dans ce jar (confirmé : même bug reproductible
 * sans aucun de mes patchs polyblur, donc préexistant rien à voir avec le
 * motion blur) l'appel plantait le jeu au boot direct pas de try/catch autour
 * juste autour de la boucle des uniforms en dessous
 *
 * fix : tout le bloc (chargement + boucle uniforms) dans un seul try/catch
 * large qui avale l'échec silencieusement comme le reste du code fait déjà
 * pour ce genre de ressource manquante au lieu de laisser planter le jeu
 */
import generated.Strings3;
import generated.Strings8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.lwjgl.opengl.GL11;

public abstract class lIIIlIIIllIllIIIlllIlllII
extends IlIllllIIlIIllIlIlllllIlI {
    public static lIIIllIlIIIlIllIlIIllllIl lIllIllllIllIlIIIllIIllll;
    public static lIIlIllIlIIllIIllIlIlllIl lllIlIlllIIlIlIIlIlllIIlI;
    public static lIIlIllIlIIllIIllIlIlllIl lllIllIllIlIIIlllIIllllII;
    public static double IIIIIIllIlIIIIlIlllIllllI = 30.0;
    public lIIlIllIlIIIIIlllIlllIIIl lIIIlIIIlIllIlllIIIIIlIlI;
    public float lIIllIIlIIIllIlIIllIIlIll = 0.0f;
    public static long IIIIlllIIIlIlIlIIIIIlllIl;
    public static IIlIllIIllllIIlllIllIllll lIlIlIIIIlIlIlllIIIllllIl;
    public boolean IllllIllIIIlllIIllllllIII;
    public static double IIlIIIllIIIlIlllIIIIllllI = 1.0;
    public List<llIIllllllIIIIlIlIIllIlII> lIllllIIllIllllllIllIIIll = Collections.synchronizedList(new ArrayList<llIIllllllIIIIlIlIIllIlII>(this.lIllllIIllIllllllIllIIIll()));
    public float llIIIllIIlIIIlIllIllIIlII;
    public List<IIIIIlIIIIIllIlIlIllIIIll> IlIllIIIIlIllllIlIIlIIlll;
    public static IIIllllIIIllIIIlIllIIllIl llIIlIlIllIlIIIllIllllIlI;
    public float IIIlIlIIlllllIIIlllIllIll;

    @Override
    public void lIlIlIIIIllllIIlllllllIlI() {
        super.lIlIlIIIIllllIIlllllllIlI();
        this.lllIllIllIlIIIlllIIllllII();
        for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
            llIIllllllIIIIlIlIIllIlII2.llIIlIlIllIlIIIllIllllIlI();
        }
    }

    public static IIIllllIIIllIIIlIllIIllIl lIlIIllIlIlIIlIlllIIllIII() {
        return llIIlIlIllIlIIIllIllllIlI;
    }

    public static void lIIllIIlIIIllIlIIllIIlIll(float f, float f2, float f3, float f4, float f5, int n) {
        int n2 = 0x7C75AD25 & 0x40AF2727;
        int n3 = 0x30FFFFFF & 0xA0FFFFFF;
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f, f2, f3, f4, f5, n2, n3, n);
    }

    public static void lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2, float f, float f2, float f3, int n) {
        float f4 = f * 2.0f;
        float f5 = f * 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIIIIIlIIlIIIIIlIllIlll().lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl2);
        GL11.glBegin(0x69F & 0x807);
        GL11.glTexCoord2d(f6 / f, f7 / f);
        GL11.glVertex2d(f2, f3);
        GL11.glTexCoord2d(f6 / f, (f7 + f) / f);
        GL11.glVertex2d(f2, f3 + f5);
        GL11.glTexCoord2d((f6 + f) / f, (f7 + f) / f);
        GL11.glVertex2d(f2 + f4, f3 + f5);
        GL11.glTexCoord2d((f6 + f) / f, f7 / f);
        GL11.glVertex2d(f2 + f4, f3);
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(int n, int n2, float f) {
        float f2 = lIIIlIIIllIllIIIlllIlllII.IIIlIllIlIIlIlIIIlIlIlIll();
        float f3 = (float)n / f2;
        float f4 = (float)n2 / f2;
        this.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.llIllIlIlIIIIlIIIIllIllll();
        GL11.glScalef(f2, f2, f2);
        this.lllIllIllIlIIIlllIIllllII(f3, f4);
        for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
            llIIllllllIIIIlIlIIllIlII2.lllIllIllIlIIIlllIIllllII(f3, f4, this.lllIllIllIlIIIlllIIllllII(llIIllllllIIIIlIlIIllIlII2, f3, f4, new llIIllllllIIIIlIlIIllIlII[0xFFFFD114 & 0x840]));
        }
        lIIllIlIIlllIlIlIlllIlIlI.IIIllIIIlllIIIIlIlIIIIlIl();
    }

    public abstract void lllIllIllIlIIIlllIIllllII(float var1, float var2, int var3);

    public static float IIIlIllIlIIlIlIIIlIlIlIll() {
        float f;
        switch (llIIlIlIllIlIIIllIllllIlI.IIIlIlIIlllllIIIlllIllIll()) {
            case 1: {
                f = 0.6666667f * 0.75f;
                break;
            }
            case 2: {
                f = 1.0f;
                break;
            }
            case 3: {
                f = 0.01724138f * 87.0f;
                break;
            }
            case 4: {
                f = 2.0f;
                break;
            }
            default: {
                f = 1.0f;
            }
        }
        return 1.0f / f;
    }

    public static void lllIllIllIlIIIlllIIllllII(double d, double d2, double d3, double d4, int n, float f) {
        d3 = d + d3;
        d4 = d2 + d4;
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n);
        GL11.glLineWidth(f);
        lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0xFFFF8649 & 0x4081, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d, d2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d3, d4, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public static void IllllIllIIIlllIIllllllIII(float f, float f2, float f3, float f4, float f5, int n) {
        int n2;
        double d = f + f3;
        double d2 = f2 + f4;
        float f6 = (float)(n >> (0x2DC & 0x2918) & (0x2FF & 0x4CFF)) / (0.3846154f * 663.0f);
        float f7 = (float)(n >> (0x20B0 & 0x250) & (0xFFFFA2FF & 0x8FF)) / (0.825f * 309.0909f);
        float f8 = (float)(n >> (0x2489 & 0x4308) & (0xFFFF81FF & 0x30FF)) / (218.57144f * 1.1666666f);
        float f9 = (float)(n & (0xFF & 0xFFFF88FF)) / (210.0f * 1.2142857f);
        GL11.glPushAttrib(0xFFFFC00F & 0x20A0);
        GL11.glScaled(1.4509803921568627 * 0.3445945945945946, 0.28 * 1.7857142857142856, 0.6847826086956522 * 0.7301587301587301);
        f *= 2.0f;
        f2 *= 2.0f;
        d *= 3.1372549019607847 * 0.6375;
        d2 *= 0.47058823529411764 * 4.25;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f7, f8, f9, f6);
        GL11.glEnable(0xB70 & 0x6F2E);
        GL11.glBegin(0x409 & 0x4029);
        for (n2 = 0x401B & 0x2220; n2 <= (0x55B & 0x187A); n2 += 3) {
            GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (3.3161255787892263 * 0.9473684210526315) / (406.6666666666667 * 0.4426229508196721)) * (double)(f5 * (-5.111111f * 0.19565217f)), (double)(f2 + f5) + Math.cos((double)n2 * (0.1891891891891892 * 16.60556116897462) / (127.82608695652173 * 1.4081632653061225)) * (double)(f5 * (3.5714285f * -0.28f)));
        }
        for (n2 = 0x255A & 0xFFFF907F; n2 <= (0x6BF & 0x8F4); n2 += 3) {
            GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (1.8823529411764706 * 1.6689710972195777) / (0.34328358208955223 * 524.3478260869565)) * (double)(f5 * (1.0555556f * -0.9473684f)), d2 - (double)f5 + Math.cos((double)n2 * (10.512252340858153 * 0.2988505747126437) / (0.8888888888888888 * 202.5)) * (double)(f5 * (0.71428573f * -1.4f)));
        }
        for (n2 = 0xFFFF801D & 0x7C0; n2 <= (0xFFFF905A & 0x5A); n2 += 3) {
            GL11.glVertex2d(d - (double)f5 + Math.sin((double)n2 * (30.0 * 0.10471975511965977) / (63.692307692307686 * 2.8260869565217392)) * (double)f5, d2 - (double)f5 + Math.cos((double)n2 * (1.8444444444444446 * 1.7032731254402491) / (0.18292682926829268 * 984.0)) * (double)f5);
        }
        for (n2 = 0x2A7A & 0xFFFF90DF; n2 <= (0x72B7 & 0x1FC); n2 += 3) {
            GL11.glVertex2d(d - (double)f5 + Math.sin((double)n2 * (3.0689655172413794 * 1.0236650219562247) / (332.3076923076923 * 0.5416666666666666)) * (double)f5, (double)(f2 + f5) + Math.cos((double)n2 * (3.42719198573432 * 0.9166666666666666) / (143.57142857142856 * 1.2537313432835822)) * (double)f5);
        }
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0xFFFFFBA4 & 0xF62);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        GL11.glScaled(1.3181818181818181 * 1.517241379310345, 6.0 * 0.3333333333333333, 4.4375 * 0.4507042253521127);
        GL11.glPopAttrib();
    }

    public abstract void IllllIllIIIlllIIllllllIII();

    public List lllIllIllIIIIllIIlIIlIlll() {
        return this.lIllllIIllIllllllIllIIIll;
    }

    public static void lllIllIllIlIIIlllIIllllII(double d, double d2, double d3, double d4, double d5, double d6) {
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        GL11.glEnable(0x2B30 & 0xB23);
        for (double d7 = d5; d7 < d6; d7 += 0.15463917525773196 * 3.2333333333333334) {
            double d8 = d7 * (7.853981633974483 * 0.4) / (0.9761904761904762 * 184.39024390243904);
            double d9 = (d7 - 1.0) * (0.02247191011235955 * 139.8008730847458) / (4.3076923076923075 * 41.785714285714285);
            double[] dArray = new double[0x1C04 & 0x45];
            dArray[0xFFFFAE05 & 0x188] = Math.cos(d8) * d3;
            dArray[0x6011 & 0x1001] = -Math.sin(d8) * d3;
            dArray[0xA & 0xFFFFF867] = Math.cos(d9) * d3;
            dArray[0x482B & 0x2413] = -Math.sin(d9) * d3;
            double[] dArray2 = dArray;
            double[] dArray3 = new double[0x1214 & 0xFFFFEC0C];
            dArray3[0x200 & 0xFFFF9C21] = Math.cos(d8) * d4;
            dArray3[0xFFFF8CA9 & 0x2111] = -Math.sin(d8) * d4;
            dArray3[0x10D6 & 0xFFFF8B02] = Math.cos(d9) * d4;
            dArray3[0x6043 & 0xFFFF8003] = -Math.sin(d9) * d4;
            double[] dArray4 = dArray3;
            lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0xFFFFC407 & 0x1117, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray4[0xFFFFA0B4 & 0x4140], d2 + dArray4[0x803 & 0xFFFF91C1], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray4[0xFFFF890F & 0x60D2], d2 + dArray4[0x6203 & 0x73], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray2[0x66 & 0x4B12], d2 + dArray2[0x443 & 0x207], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray2[0x1414 & 0x6820], d2 + dArray2[0x30C9 & 0xFFFFC203], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0x1B20 & 0xFFFF8B20);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
    }

    public static void lllIllIllIlIIIlllIIllllII(int n) {
        float f = (float)(n >> (0xFFFFA219 & 0xCB8) & (0xFFFF80FF & 0x43FF)) / (0.8548387f * 298.30188f);
        float f2 = (float)(n >> (0x7419 & 0x1D0) & (0xFFFF92FF & 0x8FF)) / (10.851064f * 23.5f);
        float f3 = (float)(n >> (0xFFFF811C & 0x4008) & (0xFF & 0xFFFF89FF)) / (114.92958f * 2.21875f);
        float f4 = (float)(n & (0x1FF & 0x8FF)) / (324.54547f * 0.78571427f);
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIlIIIlllIIllllII(0xFFFFC353 & 0x382, 0x63B7 & 0x1703, 0x102D & 0x193, 0xFFFFFC0E & 0x41);
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(0x1D81 & 0x7D23);
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f2, f3, f4, f);
    }

    public abstract void llIIlIlIllIlIIIllIllllIlI();

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, float f5, int n, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        double d = f5 - 1.0f;
        int n2 = 0x40C & 0xFFFF89C4;
        int n3 = 0xFFFFFB00 & 7;
        if (bl4) {
            lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f3 - f5 + 1.0f), (double)(f2 + f4 - f5 + 1.0f), (double)f5, d, 0.0, n2, (double)n3, n);
        } else {
            lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f + f3 - f5 + 1.0f, f + f3, f2 + f4, n);
            lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f + f3, f2 + f4 - f5, f2 + f4, n);
        }
        if (bl3) {
            lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f5 - 1.0f), (double)(f2 + f4 - f5 + 1.0f), (double)f5, d, 1.0, n2, (double)n3, n);
        } else {
            lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f - 1.0f, f2 + f4 - f5, f2 + f4, n);
            lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f - 1.0f, f + f5 - 2.0f, f2 + f4, n);
        }
        if (bl) {
            lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f5 - 1.0f), (double)(f2 + f5 - 1.0f), (double)f5, d, 0.7222222222222222 * 2.769230769230769, n2, (double)n3, n);
        } else {
            lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f - 1.0f, f + f5 - 2.0f, f2 - 1.0f, n);
            lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f - 1.0f, f2 - 1.0f, f2 + f5 - 1.0f, n);
        }
        if (bl2) {
            lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f3 - f5 + 1.0f), (double)(f2 + f5 - 1.0f), (double)f5, d, 0.5463917525773195 * 5.490566037735849, n2, (double)n3, n);
        } else {
            lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f + f3 - f5 + 1.0f, f + f3 - 1.0f, f2 - 1.0f, n);
            lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f + f3, f2 - 2.0f, f2 + f5 - 1.0f, n);
        }
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f + f5 - 1.0f, f + f3 - f5, f2 + f4, n);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f + f5 - 1.0f, f + f3 - f5, f2 - 1.0f, n);
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f - 1.0f, f2 + f5 - 2.0f, f2 + f4 - f5 + 1.0f, n);
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f + f3, f2 + f5 - 2.0f, f2 + f4 - f5 + 1.0f, n);
    }

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, int n, int n2, int n3, int n4) {
        float f3 = 7.857143f * 4.971591E-4f;
        float f4 = 1.7959183f * 0.002175071f;
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x60F & 0x7007, lllIIIlllIlIlllIlIIllIlIl.lIllllIIllIllllllIllIIIll);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(f + 0.0f), (double)(f2 + (float)n4), 0.0).lllIllIllIlIIIlllIIllllII((float)(n + (0x221C & 0x4A2)) * f3, (float)(n2 + n4) * f4).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(f + (float)n3), (double)(f2 + (float)n4), 0.0).lllIllIllIlIIIlllIIllllII((float)(n + n3) * f3, (float)(n2 + n4) * f4).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(f + (float)n3), (double)(f2 + 0.0f), 0.0).lllIllIllIlIIIlllIIllllII((float)(n + n3) * f3, (float)(n2 + (0xFFFF9104 & 0x2C00)) * f4).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(f + 0.0f), (double)(f2 + 0.0f), 0.0).lllIllIllIlIIIlllIIllllII((float)(n + (0x2206 & 0xFFFFC940)) * f3, (float)(n2 + (0 & 0x496D)) * f4).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
    }

    public static void lllIllIllIlIIIlllIIllllII(IIIllllIIIllIIIlIllIIllIl iIIllllIIIllIIIlIllIIllIl) {
        llIIlIlIllIlIIIllIllllIlI = iIIllllIIIllIIIlIllIIllIl;
    }

    public static void IlIllIIIIlIllllIlIIlIIlll(float f, float f2, float f3, int n) {
        if (f2 < f) {
            float f4 = f;
            f = f2;
            f2 = f4;
        }
        lIlIIlllIIllIlIllIIlIlIlI.IlIllIIIIlIllllIlIIlIIlll(f, f3, f2 + 1.0f, f3 + 1.0f, n);
    }

    public static void IIIlIlIIlllllIIIlllIllIll(float f, float f2, float f3, float f4, float f5, int n) {
        double d = f + f3;
        double d2 = f2 + f4;
        float f6 = (float)(n >> (0x411A & 0xFFFF805D) & (0xFF & 0xFFFF81FF)) / (0.5058824f * 504.06973f);
        float f7 = (float)(n >> (0x407A & 0xFFFFB291) & (0xBFF & 0x60FF)) / (306.0f * 0.8333333f);
        float f8 = (float)(n >> (0x5088 & 0xFFFFA429) & (0xFF & 0xFFFFE0FF)) / (0.78571427f * 324.54547f);
        float f9 = (float)(n & (0xFFFFC0FF & 0x5FF)) / (298.0986f * 0.85542166f);
        GL11.glPushAttrib(0xFFFF820C & 0x810);
        GL11.glScaled(1.7 * 0.29411764705882354, 4.1000000000000005 * 0.12195121951219512, 1.9000000000000001 * 0.2631578947368421);
        f *= 2.0f;
        f2 *= 2.0f;
        d *= 2.8285714285714287 * 0.7070707070707071;
        d2 *= 1.3970588235294117 * 1.431578947368421;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f7, f8, f9, f6);
        GL11.glEnable(0xB2A & 0xF35);
        GL11.glBegin(0xFFFFA549 & 0x120D);
        GL11.glVertex2d(f, f2);
        GL11.glVertex2d(f, d2);
        GL11.glVertex2d(d, d2);
        for (int i = 0xFFFFA05A & 0xDF; i <= (0xFFFFA5B6 & 0x42B4); i += 3) {
            GL11.glVertex2d(d - (double)f5 + Math.sin((double)i * (1.053191489361702 * 2.982926357953945) / (46.391752577319586 * 3.88)) * (double)f5, (double)(f2 + f5) + Math.cos((double)i * (4.370911518037973 * 0.71875) / (1.1733333333333333 * 153.4090909090909)) * (double)f5);
        }
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0xB20 & 0x3B68);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        GL11.glScaled(2.3209876543209877 * 0.8617021276595744, 1.625 * 1.2307692307692308, 2.2857142857142856 * 0.875);
        GL11.glPopAttrib();
    }

    public static void llIIIllIIlIIIlIllIllIIlII(float f, float f2, float f3, float f4, float f5, int n) {
        double d = f + f3;
        double d2 = f2 + f4;
        float f6 = (float)(n >> (0x119 & 0x1058) & (0x24FF & 0x2FF)) / (186.34615f * 1.3684211f);
        float f7 = (float)(n >> (0x3992 & 0x11) & (0xFFFFC6FF & 0x10FF)) / (1366.0714f * 0.18666667f);
        float f8 = (float)(n >> (0x20A & 0xFFFFB80C) & (0x2FF & 0xFF)) / (267.5926f * 0.9529412f);
        float f9 = (float)(n & (0x10FF & 0x44FF)) / (0.42857143f * 595.0f);
        GL11.glPushAttrib(0x3C10 & 0x28);
        GL11.glScaled(0.1875 * 2.6666666666666665, 0.14130434782608695 * 3.5384615384615388, 1.0394736842105263 * 0.4810126582278481);
        f *= 2.0f;
        f2 *= 2.0f;
        d *= 2.6666666666666665 * 0.75;
        d2 *= 4.325581395348838 * 0.46236559139784944;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f7, f8, f9, f6);
        GL11.glEnable(0x2B70 & 0x4B29);
        GL11.glBegin(0x39 & 0x6BC9);
        GL11.glVertex2d(f, f2);
        GL11.glVertex2d(f, d2);
        for (int i = 0xFFFF8484 & 0x2913; i <= (0x2E7E & 0xFFFFC05B); i += 3) {
            GL11.glVertex2d(d - (double)f5 + Math.sin((double)i * (1.5945945945945945 * 1.9701513251325822) / (3.4444444444444446 * 52.25806451612903)) * (double)f5, d2 - (double)f5 + Math.cos((double)i * (10.09797638653862 * 0.3111111111111111) / (0.8289473684210527 * 217.14285714285714)) * (double)f5);
        }
        GL11.glVertex2d(d, f2);
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0xB20 & 0xF68);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        GL11.glScaled(1.7872340425531914 * 1.119047619047619, 1.8333333333333335 * 1.0909090909090908, 47.333333333333336 * 0.04225352112676056);
        GL11.glPopAttrib();
    }

    public abstract void llIIlIlIllIlIIIllIllllIlI(float var1, float var2, int var3);

    public static void lllIIllllIIlIIIlIIIIllIlI() {
        lIIllIlIIlllIlIlIlllIlIlI.lllIlIlllIIlIlIIlIlllIIlI(0x1D01 & 0x7D8A);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllIIIIlIllllIlIIlIIlll();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
    }

    public static void lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2, float f, float f2, float f3, float f4) {
        float f5 = f3 / 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIIIIIlIIlIIIIIlIllIlll().lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl2);
        GL11.glBegin(0xFFFF9317 & 0x60CF);
        GL11.glTexCoord2d(f6 / f5, f7 / f5);
        GL11.glVertex2d(f, f2);
        GL11.glTexCoord2d(f6 / f5, (f7 + f5) / f5);
        GL11.glVertex2d(f, f2 + f4);
        GL11.glTexCoord2d((f6 + f5) / f5, (f7 + f5) / f5);
        GL11.glVertex2d(f + f3, f2 + f4);
        GL11.glTexCoord2d((f6 + f5) / f5, f7 / f5);
        GL11.glVertex2d(f + f3, f2);
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
    }

    public abstract void lllIllIllIlIIIlllIIllllII(char var1, int var2);

    public abstract void lIIllIIlIIIllIlIIllIIlIll();

    public static void lllIllIllIlIIIlllIIllllII(double d, double d2, double d3, double d4, double d5, int n, double d6) {
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        GL11.glEnable(0xFFFF8B70 & 0xF21);
        d5 = (d5 + (double)n) % (double)n;
        for (double d7 = 50.625 * 7.111111111111111 / (double)n * d5; d7 < 280.67796610169495 * 1.2826086956521738 / (double)n * (d5 + d6); d7 += 1.0) {
            double d8 = d7 * (0.3979591836734694 * 7.894258462866659) / (93.41772151898735 * 1.9268292682926829);
            double d9 = (d7 - 1.0) * (0.07368421052631578 * 42.635900298718624) / (414.0 * 0.43478260869565216);
            double[] dArray = new double[0xFFFFCE44 & 0xAE];
            dArray[0x401 & 0xFFFF8828] = Math.cos(d8) * d3;
            dArray[0x617 & 0xFFFF8121] = -Math.sin(d8) * d3;
            dArray[0x1A02 & 0xFFFF8083] = Math.cos(d9) * d3;
            dArray[0x200B & 0xFFFF8207] = -Math.sin(d9) * d3;
            double[] dArray2 = dArray;
            double[] dArray3 = new double[0xFFFF8496 & 0x4C];
            dArray3[0x2C20 & 0x289] = Math.cos(d8) * d4;
            dArray3[0x29 & 0xFFFFA985] = -Math.sin(d8) * d4;
            dArray3[0x7406 & 0xFFFF8333] = Math.cos(d9) * d4;
            dArray3[0x4013 & 0x10AF] = -Math.sin(d9) * d4;
            double[] dArray4 = dArray3;
            lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0xFFFF8147 & 0x4807, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray4[0 & 0x203A], d2 + dArray4[0x4005 & 0x1783], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray4[0x903 & 0x7082], d2 + dArray4[0x1B07 & 0x4003], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray2[0x234A & 0x892], d2 + dArray2[0x300F & 0x4103], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray2[0x801 & 0x250C], d2 + dArray2[0xFFFFC851 & 0x2029], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        }
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0x1BE0 & 0x2F22);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
    }

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, float f5, int n, int n2, int n3) {
        lIIIlIIIllIllIIIlllIlllII.IllllIllIIIlllIIllllllIII(f, f2, f3, f4, f5 += 1.0f, n3);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f, f2, f3, f4, f5 - 1.0f, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f + 1.0f, f2 + 1.0f, f3 - 2.0f, f4 - 2.0f, f5 - 0.4050633f * 5.5546875f, n2);
    }

    public static void lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2, float f, float f2, float f3) {
        float f4 = f * 2.0f;
        float f5 = f * 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIIIIIlIIlIIIIIlIllIlll().lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl2);
        GL11.glBegin(0xFFFF9107 & 0x47);
        GL11.glTexCoord2d(f6 / f, f7 / f);
        GL11.glVertex2d(f2, f3);
        GL11.glTexCoord2d(f6 / f, (f7 + f) / f);
        GL11.glVertex2d(f2, f3 + f5);
        GL11.glTexCoord2d((f6 + f) / f, (f7 + f) / f);
        GL11.glVertex2d(f2 + f4, f3 + f5);
        GL11.glTexCoord2d((f6 + f) / f, f7 / f);
        GL11.glVertex2d(f2 + f4, f3);
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
    }

    @Override
    public void llIIlIlIllIlIIIllIllllIlI(char c, int n) {
        super.llIIlIlIllIlIIIllIllllIlI(c, n);
        this.lllIllIllIlIIIlllIIllllII(c, n);
        for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
            llIIllllllIIIIlIlIIllIlII2.lllIllIllIlIIIlllIIllllII(c, n);
        }
    }

    public static void IIIlIlIIlllllIIIlllIllIll(float f, float f2, float f3, int n) {
        if (f3 < f2) {
            float f4 = f2;
            f2 = f3;
            f3 = f4;
        }
        lIlIIlllIIllIlIllIIlIlIlI.IlIllIIIIlIllllIlIIlIIlll(f, f2 + 1.0f, f + 1.0f, f3, n);
    }

    public float lllllIIIlIIllllllllIlllIl() {
        return this.llIIIllIIlIIIlIllIllIIlII;
    }

    public static void llIIlIlIllIlIIIllIllllIlI(float f, float f2, float f3, int n) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n);
        float f4 = (f -= f3 / 2.0f) + f3;
        float f5 = f2 + f3;
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x404 & 0x325, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f4, (double)f5, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f4, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f, (double)(f2 + (f5 - f2) / 2.0f), 0.0).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public static void lllIllIllIlIIIlllIIllllII(int n, int n2, int n3, int n4, float f, int n5) {
        int n6 = n4 - n2;
        int n7 = n3 - n;
        int n8 = n5 - n4;
        GL11.glScissor((int)((float)n * f), (int)((float)n8 * f), (int)((float)n7 * f), (int)((float)n6 * f));
    }

    public static void IlIllIIIIlIllllIlIIlIIlll(float f, float f2, float f3, float f4, float f5, int n) {
        int n2;
        double d = f + f3;
        double d2 = f2 + f4;
        float f6 = (float)(n >> (0x5E & 0xFFFF9238) & (0x8FF & 0x42FF)) / (4.142857f * 61.551723f);
        float f7 = (float)(n >> (0x21FC & 0x812) & (0xFFFFA0FF & 0x18FF)) / (102.0f * 2.5f);
        float f8 = (float)(n >> (0xFFFFB618 & 0x4088) & (0xFFFFC8FF & 0x2FF)) / (1402.5f * 0.18181819f);
        float f9 = (float)(n & (0xFFFF80FF & 0x1FF)) / (150.91837f * 1.6896552f);
        GL11.glPushAttrib(0x4000 & 0x2B32);
        GL11.glScaled(0.7540983606557377 * 0.6630434782608696, 0.6176470588235294 * 0.8095238095238095, 6.357142857142858 * 0.07865168539325842);
        f *= 2.0f;
        f2 *= 2.0f;
        d *= 1.558139534883721 * 1.2835820895522387;
        d2 *= 2.6 * 0.7692307692307692;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f7, f8, f9, f6);
        GL11.glEnable(0xBE0 & 0xFFFFEB35);
        GL11.glBegin(0x2019 & 0x1A09);
        for (n2 = 0xFFFFE882 & 0x728; n2 <= (0x205A & 0x45E); n2 += 3) {
            GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (1.8849555921538759 * 1.6666666666666667) / (0.18518518518518517 * 972.0)) * (double)(f5 * (0.45555556f * -2.195122f)), (double)(f2 + f5) + Math.cos((double)n2 * (3.1028075591010302 * 1.0125) / (0.6875 * 261.8181818181818)) * (double)(f5 * (8.5f * -0.11764706f)));
        }
        for (n2 = 0xC5E & 0x7A; n2 <= (0x34B4 & 0x1B4); n2 += 3) {
            GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (1.2011971910784502 * 2.6153846153846154) / (1440.0 * 0.125)) * (double)(f5 * (0.25974026f * -3.85f)), d2 - (double)f5 + Math.cos((double)n2 * (0.4780684472854033 * 6.571428571428571) / (48.57142857142857 * 3.7058823529411766)) * (double)(f5 * (0.011363637f * -88.0f)));
        }
        GL11.glVertex2d(d, d2);
        GL11.glVertex2d(d, f2);
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0x6B20 & 0x1FB3);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        GL11.glScaled(1.6521739130434783 * 1.2105263157894737, 0.7096774193548387 * 2.818181818181818, 1.875 * 1.0666666666666667);
        GL11.glPopAttrib();
    }

    public static void llIllllllIllllllllIllIIll() {
        if (System.currentTimeMillis() - IIIIlllIIIlIlIlIIIIIlllIl > (0xA044333L & 0x2797DF19001238F6L)) {
            IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllllIlIllIlIlllIIIlIIlIl().lllIllIllIlIIIlllIIllllII(lIlllllllIllIIIllIIIIlIIl.lllIllIllIlIIIlllIIllllII(lllIlIlllIIlIlIIlIlllIIlI, 1.0f));
            IIIIlllIIIlIlIlIIIIIlllIl = System.currentTimeMillis();
        }
    }

    public static void lIllllIIllIllllllIllIIIll(float f, float f2, float f3, float f4, float f5, int n) {
        int n2;
        double d = f + f3;
        double d2 = f2 + f4;
        float f6 = (float)(n >> (0x181B & 0x4278) & (0xFFFF80FF & 0x68FF)) / (22185.0f * 0.011494253f);
        float f7 = (float)(n >> (0xFFFFA811 & 0x5012) & (0xCFF & 0x1FF)) / (0.64179105f * 397.3256f);
        float f8 = (float)(n >> (0x4088 & 0x2639) & (0xFFFFA0FF & 0xFF)) / (0.8f * 318.75f);
        float f9 = (float)(n & (0x5FF & 0x18FF)) / (0.03508772f * 7267.5f);
        GL11.glPushAttrib(0xC86 & 0xFFFFE209);
        GL11.glScaled(1.0 * 0.5, 8.4 * 0.05952380952380952, 1.7272727272727273 * 0.2894736842105263);
        f *= 2.0f;
        f2 *= 2.0f;
        d *= 1.1296296296296295 * 1.7704918032786887;
        d2 *= 3.9428571428571426 * 0.5072463768115942;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f7, f8, f9, f6);
        GL11.glEnable(0x2B21 & 0xFFFF8F30);
        GL11.glBegin(0x6029 & 0x138F);
        for (n2 = 0x400 & 0x4002; n2 <= (0x65DA & 0xFFFF905A); n2 += 3) {
            GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (1.1153846153846154 * 2.816600310114987) / (7.105263157894737 * 25.333333333333332)) * (double)(f5 * (0.6181818f * -1.617647f)), (double)(f2 + f5) + Math.cos((double)n2 * (5.026548245743669 * 0.625) / (0.5760869565217391 * 312.45283018867923)) * (double)(f5 * (-0.15f * 6.6666665f)));
        }
        GL11.glVertex2d(f, d2);
        GL11.glVertex2d(d, d2);
        for (n2 = 0x48FA & 0x245A; n2 <= (0x4BD & 0xFFFF98B6); n2 += 3) {
            GL11.glVertex2d(d - (double)f5 + Math.sin((double)n2 * (0.058823529411764705 * 53.40707511102649) / (237.5 * 0.7578947368421053)) * (double)f5, (double)(f2 + f5) + Math.cos((double)n2 * (5.235987755982989 * 0.6) / (692.3076923076923 * 0.26)) * (double)f5);
        }
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0xFFFFCB22 & 0xF38);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        GL11.glScaled(3.0 * 0.6666666666666666, 0.8266666666666667 * 2.4193548387096775, 4.666666666666667 * 0.42857142857142855);
        GL11.glPopAttrib();
    }

    @Override
    public void IIlllllllllIlIllIlIlIIllI() {
        this.lIIllIIlIIIllIlIIllIIlIll();
        for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
            llIIllllllIIIIlIlIIllIlII2.lllIllIllIlIIIlllIIllllII();
        }
    }

    @Override
    public void IlIllllIllllllllIIIlIIlII() {
        if (this.IllllIllIIIlllIIllllllIII) {
            try {
                this.lIlIlIIIIllllIIlllllllIlI.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI(lllIllIllIlIIIlllIIllllII);
                if (this.lIlIlIIIIllllIIlllllllIlI.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII()) {
                    IIlIIllIIIIIlIllIIIIlllIl iIlIIllIIIIIlIllIIIIlllIl = lIIIIIIllIIIIIIIlllllllII.IIIIlllIIIlIlIlIIIIIlllIl().lIlIlIIIIllllIIlllllllIlI.llIIIllIIlIIIlIllIllIIlII();
                    List list = iIlIIllIIIIIlIllIIIIlllIl.lllIllIllIlIIIlllIIllllII;
                    for (Object listObj : list) {
                        IlllIllIIIIIIIIllIlllIlll illlIllIIIIIIIIllIlllIlll = (IlllIllIIIIIIIIllIlllIlll) listObj;
                        IIIIIlIIIIIllIlIlIllIIIll iIIIIlIIIIIllIlIlIllIIIll = illlIllIIIIIIIIllIlllIlll.llIIlIlIllIlIIIllIllllIlI().lllIllIllIlIIIlllIIllllII(Strings8.strings[0x235F & 0x7FF]);
                        if (iIIIIlIIIIIllIlIlIllIIIll == null) continue;
                        this.IlIllIIIIlIllllIlIIlIIlll.add(iIIIIlIIIIIllIlIlIllIIIll);
                        iIIIIlIIIIIllIlIlIllIIIll.lllIllIllIlIIIlllIIllllII(this.lIIllIIlIIIllIlIIllIIlIll);
                    }
                }
            }
            catch (Exception exception) {
                // shader "menu_blur" pas dispo dans ce jar echoue silencieusement au lieu de planter le jeu
            }
            this.IllllIllIIIlllIIllllllIII = false;
        }
        this.lIIIlIIIlIllIlllIIIIIlIlI.lllIllIllIlIIIlllIIllllII((long)((float)this.lIIIlIIIlIllIlllIIIIIlIlI.IIIlIllIlIIlIlIIIlIlIlIll() * this.lIIllIIlIIIllIlIIllIIlIll));
        this.llIIlIlIllIlIIIllIllllIlI();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void lllIllIllIlIIIlllIIllllII(int n, int n2, int n3) {
        block7: {
            float f = lIIIlIIIllIllIIIlllIlllII.IIIlIllIlIIlIlIIIlIlIlIll();
            float f2 = (float)n / f;
            float f3 = (float)n2 / f;
            this.lllIllIllIlIIIlllIIllllII(f2, f3, n3);
            try {
                for (llIIllllllIIIIlIlIIllIlII object2 : this.lIllllIIllIllllllIllIIIll) {
                    object2.llIIlIlIllIlIIIllIllllIlI(f2, f3, n3);
                }
                Object object3 = null;
                for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
                    if (!llIIllllllIIIIlIlIIllIlII2.lllIllIllIlIIIlllIIllllII(f2, f3) || !this.lllIllIllIlIIIlllIIllllII(llIIllllllIIIIlIlIIllIlII2, f2, f3, new llIIllllllIIIIlIlIIllIlII[0xFFFFAF80 & 4]) || !llIIllllllIIIIlIlIIllIlII2.lllIllIllIlIIIlllIIllllII(f2, f3, n3)) continue;
                    object3 = llIIllllllIIIIlIlIIllIlII2;
                    lIIIlIIIllIllIIIlllIlllII.llIllllllIllllllllIllIIll();
                    break;
                }
                if (object3 == null) break block7;
                Object syncLock = this.lIllllIIllIllllllIllIIIll;
                synchronized (syncLock) {
                    this.lIllllIIllIllllllIllIIIll.add(this.lIllllIIllIllllllIllIIIll.remove(this.lIllllIIllIllllllIllIIIll.indexOf(object3)));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public abstract List<llIIllllllIIIIlIlIIllIlII> lIllllIIllIllllllIllIIIll();

    @Override
    public void lllIllIllIlIIIlllIIllllII(lIIIIIIllIIIIIIIlllllllII lIIIIIIllIIIIIIIlllllllII2, int n, int n2) {
        this.lIlIlIIIIllllIIlllllllIlI = lIIIIIIllIIIIIIIlllllllII2;
        this.lIlIIllIlIlIIlIlllIIllIII = IlIIllIIllIIIllllIIllIIII.lIIIlIIIlIllIlllIIIIIlIlI();
        this.IIIlIllIlIIlIlIIIlIlIlIll = n;
        this.IIlIIIIIIlllllllllIIIIIII = n2;
        this.lllllIIIlIIllllllllIlllIl.clear();
        llIIlIlIllIlIIIllIllllIlI = new IIIllllIIIllIIIlIllIIllIl(lIIIIIIllIIIIIIIlllllllII2);
        float f = lIIIlIIIllIllIIIlllIlllII.IIIlIllIlIIlIlIIIlIlIlIll();
        this.IIIlIlIIlllllIIIlllIllIll = (float)n / f;
        this.llIIIllIIlIIIlIllIllIIlII = (float)n2 / f;
        this.IlIllllIllllllllIIIlIIlII();
    }

    @Override
    public void llIIlIlIllIlIIIllIllllIlI(int n, int n2, int n3) {
        float f = lIIIlIIIllIllIIIlllIlllII.IIIlIllIlIIlIlIIIlIlIlIll();
        float f2 = (float)n / f;
        float f3 = (float)n2 / f;
        this.llIIlIlIllIlIIIllIllllIlI(f2 / f, f3 / f, n3);
        for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
            if (!llIIllllllIIIIlIlIIllIlII2.lllIllIllIlIIIlllIIllllII(f2, f3) || !this.lllIllIllIlIIIlllIIllllII(llIIllllllIIIIlIlIIllIlII2, f2, f3, new llIIllllllIIIIlIlIIllIlII[0x2808 & 0x5483]) || !llIIllllllIIIIlIlIIllIlII2.IllllIllIIIlllIIllllllIII(f2, f3, n3)) continue;
            lIIIlIIIllIllIIIlllIlllII.llIllllllIllllllllIllIIll();
            break;
        }
    }

    public void IllIIllIlIlIlIlllIlIlIllI() {
        if (this.lIIIlIIIlIllIlllIIIIIlIlI.lIIllIIlIIIllIlIIllIIlIll() && this.IlIllIIIIlIllllIlIIlIIlll != null) {
            for (IIIIIlIIIIIllIlIlIllIIIll iIIIIlIIIIIllIlIlIllIIIll : this.IlIllIIIIlIllllIlIIlIIlll) {
                iIIIIlIIIIIllIlIlIllIIIll.lllIllIllIlIIIlllIIllllII(this.lIIIlIIIlIllIlllIIIIIlIlI.lIIIlIIIlIllIlllIIIIIlIlI());
            }
        }
    }

    public static void IllllIllIIIlllIIllllllIII(float f, float f2, float f3, float f4, int n) {
        float f5 = 0.65f * 6.1538463f;
        float f6 = f5 / (2.6666667f * 1.5f);
        int n2 = 0x20FFFFFF & 0x32FFFFFF;
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f, f2, f2 + f4 - f6, n2);
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f + f3 - f6, f2, f2 + f4 - f6, n2);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f, f + f3 - f6, f2, n2);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f, f + f3 - f6, f2 + f4 - f6, n2);
        lIIIlIIIllIllIIIlllIlllII.IllllIllIIIlllIIllllllIII(f, f2, f3, f4, f5, n);
    }

    static {
        lllIIIIIlIIlIIIIIlIllIlll.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII(IlIIllIIlIlIIIIlIlIIlllII.class, (Object eventObj) -> {
            IlIIllIIlIlIIIIlIlIIlllII ilIIllIIlIlIIIIlIlIIlllII = (IlIIllIIlIlIIIIlIlIIlllII) eventObj;
            lIIIlIIIllIllIIIlllIlllII lIIIlIIIllIllIIIlllIlllII2 = null;
            lIIIlIIIllIllIIIlllIlllII lIIIlIIIllIllIIIlllIlllII3 = null;
            if (IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII instanceof lIIIlIIIllIllIIIlllIlllII) {
                lIIIlIIIllIllIIIlllIlllII2 = (lIIIlIIIllIllIIIlllIlllII)IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII;
            }
            if (ilIIllIIlIlIIIIlIlIIlllII.IllllIllIIIlllIIllllllIII() instanceof lIIIlIIIllIllIIIlllIlllII) {
                lIIIlIIIllIllIIIlllIlllII3 = (lIIIlIIIllIllIIIlllIlllII)ilIIllIIlIlIIIIlIlIIlllII.IllllIllIIIlllIIllllllIII();
            }
            if (lIIIlIIIllIllIIIlllIlllII2 != null) {
                lIIIlIIIllIllIIIlllIlllII2.IllllIllIIIlllIIllllllIII = true;
                if (lIIIlIIIllIllIIIlllIlllII3 != null) {
                    lIIIlIIIllIllIIIlllIlllII3.lIIllIIlIIIllIlIIllIIlIll = lIIIlIIIllIllIIIlllIlllII2.lIIIlIIIlIllIlllIIIIIlIlI.lIIIlIIIlIllIlllIIIIIlIlI();
                }
            }
            if (lIIIlIIIllIllIIIlllIlllII3 != null) {
                lIIIlIIIllIllIIIlllIlllII3.IllllIllIIIlllIIllllllIII = true;
            }
        });
        lllIllIllIlIIIlllIIllllII = new lIIlIllIlIIllIIllIlIlllIl(Strings8.strings[0x4363 & 0x1FE4]);
        lllIlIlllIIlIlIIlIlllIIlI = new lIIlIllIlIIllIIllIlIlllIl(Strings3.strings[0x22F5 & 0x4ADF]);
        IIIIlllIIIlIlIlIIIIIlllIl = System.currentTimeMillis();
        lIlIlIIIIlIlIlllIIIllllIl = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIllIllllIllIlIIIllIIllll = lIlIlIIIIlIlIlllIIIllllIl.IllllIllIIIlllIIllllllIII();
    }

    public lIIIlIIIllIllIIIlllIlllII() {
        this.IllllIllIIIlllIIllllllIII = true;
        this.lIIIlIIIlIllIlllIIIIIlIlI = new lIIlIllIlIIIIIlllIlllIIIl(0x21000A7DL & 0x3C51680D806B707DL);
        this.IlIllIIIIlIllllIlIIlIIlll = new ArrayList();
    }

    public static void lllIllIllIlIIIlllIIllllII(int n, int n2, int n3, int n4, int n5, int n6) {
        float f = 0.88461536f * 0.004415761f;
        float f2 = 0.0033408715f * 1.1692308f;
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x1047 & 0xFFFF8207, lllIIIlllIlIlllIlIIllIlIl.lIllllIIllIllllllIllIIIll);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(n + (0x3812 & 0xFFFF8508)), (double)(n2 + n6), 0.0).lllIllIllIlIIIlllIIllllII((float)(n3 + (0x3A4 & 0x4C50)) * f, (float)(n4 + n6) * f2).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(n + n5), (double)(n2 + n6), 0.0).lllIllIllIlIIIlllIIllllII((float)(n3 + n5) * f, (float)(n4 + n6) * f2).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(n + n5), (double)(n2 + (0x4461 & 0xFFFF9A86)), 0.0).lllIllIllIlIIIlllIIllllII((float)(n3 + n5) * f, (float)(n4 + (0x4741 & 0xFFFFA092)) * f2).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(n + (0xFFFF8802 & 0x43C0)), (double)(n2 + (0x880 & 0xFFFFB708)), 0.0).lllIllIllIlIIIlllIIllllII((float)(n3 + (0x204C & 0x5F02)) * f, (float)(n4 + (0x1402 & 0x61)) * f2).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
    }

    public boolean lllIllIllIlIIIlllIIllllII(llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2, float f, float f2, llIIllllllIIIIlIlIIllIlII ... llIIllllllIIIIlIlIIllIlIIArray) {
        llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII3;
        List<llIIllllllIIIIlIlIIllIlII> list = Arrays.asList(llIIllllllIIIIlIlIIllIlIIArray);
        int n = 0x2153 & 0xFFFF8425;
        for (int i = this.lIllllIIllIllllllIllIIIll.size() - (0x31 & 0x180D); i >= 0 && (llIIllllllIIIIlIlIIllIlII3 = (llIIllllllIIIIlIlIIllIlII)this.lIllllIIllIllllllIllIIIll.get(i)) != llIIllllllIIIIlIlIIllIlII2; --i) {
            if (list.contains(llIIllllllIIIIlIlIIllIlII3) || !llIIllllllIIIIlIlIIllIlII3.lllIllIllIlIIIlllIIllllII(f, f2)) continue;
            n = 0x274 & 0xFFFFC588;
            break;
        }
        return n != 0;
    }

    public float IIlIIIIIIlllllllllIIIIIII() {
        return this.IIIlIlIIlllllIIIlllIllIll;
    }

    public abstract void lllIllIllIlIIIlllIIllllII();

    public static void llIIlIlIllIlIIIllIllllIlI(float f, float f2, float f3, float f4, float f5, int n) {
        double d = f5 - 1.0f;
        int n2 = 0x644 & 0x300E;
        int n3 = 0x2101 & 0xFFFFC8C0;
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f3 - f5 + 1.0f), (double)(f2 + f5 - 1.0f), (double)f5, d, 0.3 * 10.0, n2, (double)n3, n);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f, f + f3, f2 + f4, n);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f, f + f3 - f5, f2 - 1.0f, n);
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f + f3, f2 + f5 - 2.0f, f2 + f4, n);
    }

    public static void lllIllIllIlIIIlllIIllllII(int n, int n2, int n3, int n4) {
        if (n3 < n2) {
            int n5 = n2;
            n2 = n3;
            n3 = n5;
        }
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(n, n2 + (0xFFFFA141 & 0x5883), n + (0x2821 & 0xFFFF920D), n3, n4);
    }

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, int n) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n);
        float f5 = f + f3;
        float f6 = f2 + f4;
        lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0xFFFFC087 & 0x101F, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f6, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f5, (double)f6, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f5, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, float f5, int n, int n2) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f, f2, f3, f4, n2);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f - f5, f2 - f5, f3 + f5 * 2.0f, f5, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f - f5, f2 + f4, f3 + f5 * 2.0f, f5, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f - f5, f2, f5, f4, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(f + f3, f2, f5, f4, n);
    }

    public static void lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIIIIIlIIlIIIIIlIllIlll().lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl2);
        GL11.glBegin(0x6AF & 0x4107);
        GL11.glTexCoord2d(f2 / f, f3 / f);
        GL11.glVertex2d(f4, f5);
        GL11.glTexCoord2d(f2 / f, (f3 + f) / f);
        GL11.glVertex2d(f4, f5 + f7);
        GL11.glTexCoord2d((f2 + f) / f, (f3 + f) / f);
        GL11.glVertex2d(f4 + f6, f5 + f7);
        GL11.glTexCoord2d((f2 + f) / f, f3 / f);
        GL11.glVertex2d(f4 + f6, f5);
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
    }

    public static void lllIllIllIlIIIlllIIllllII(double d, double d2, double d3, int n) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n);
        GL11.glBlendFunc(0x2702 & 0x5382, 0x727 & 0x434B);
        lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0x1007 & 0x4106, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d, d2, 1.0).IIIlIlIIlllllIIIlllIllIll();
        double d4 = 16.664100162519773 * 0.3770491803278688;
        double d5 = d4 / (0.9368421052631579 * 32.02247191011236);
        for (double d6 = -d5; d6 < d4; d6 += d5) {
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + d3 * Math.cos(-d6), d2 + d3 * Math.sin(-d6), 8.125 * 1.2307692307692308).IIIlIlIIlllllIIIlllIllIll();
        }
        lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public static void llIIlIlIllIlIIIllIllllIlI(float f, float f2, float f3, float f4, int n) {
        float f5 = f + f3;
        float f6 = f2 + f4;
        lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0xFFFFC007 & 0x51F, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f6, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f5, (double)f6, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f5, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, float f5, int n) {
        double d = f5 - 1.0f;
        int n2 = 0xFFFF9045 & 0x2B6;
        int n3 = 0xFFFF802A & 0x3E81;
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f3 - f5 + 1.0f), (double)(f2 + f4 - f5 + 1.0f), (double)f5, d, 0.0, n2, (double)n3, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f5 - 1.0f), (double)(f2 + f4 - f5 + 1.0f), (double)f5, d, 1.0, n2, (double)n3, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f5 - 1.0f), (double)(f2 + f5 - 1.0f), (double)f5, d, 3.727272727272727 * 0.5365853658536586, n2, (double)n3, n);
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((double)(f + f3 - f5 + 1.0f), (double)(f2 + f5 - 1.0f), (double)f5, d, 1.1702127659574468 * 2.5636363636363635, n2, (double)n3, n);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f + f5 - 1.0f, f + f3 - f5, f2 + f4, n);
        lIIIlIIIllIllIIIlllIlllII.IlIllIIIIlIllllIlIIlIIlll(f + f5 - 1.0f, f + f3 - f5, f2 - 1.0f, n);
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f - 1.0f, f2 + f5 - 2.0f, f2 + f4 - f5 + 1.0f, n);
        lIIIlIIIllIllIIIlllIlllII.IIIlIlIIlllllIIIlllIllIll(f + f3, f2 + f5 - 2.0f, f2 + f4 - f5 + 1.0f, n);
    }

    public abstract void lllIllIllIlIIIlllIIllllII(float var1, float var2);

    public static void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, int n) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n);
        float f4 = (f -= f3 / 2.0f) + f3;
        float f5 = f2 + f3 / (1.8837209f * 0.7962963f);
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x500E & 0x14, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)(f + f3 / 2.0f), (double)f5, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f4, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    @Override
    public void llIIIIIIlllIlIIlIlIIllIII() {
        if (this.lIlIlIIIIllllIIlllllllIlI.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII()) {
            this.lIlIlIIIIllllIIlllllllIlI.lIlIlIIIIllllIIlllllllIlI.llIIlIlIllIlIIIllIllllIlI();
        }
        this.IllllIllIIIlllIIllllllIII();
        for (llIIllllllIIIIlIlIIllIlII llIIllllllIIIIlIlIIllIlII2 : this.lIllllIIllIllllllIllIIIll) {
            llIIllllllIIIIlIlIIllIlII2.IllllIllIIIlllIIllllllIII();
        }
    }

    public static void lllIllIllIlIIIlllIIllllII(double d, double d2, double d3, double d4, double d5, int n, double d6, int n2) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n2);
        d5 = (d5 + (double)n) % (double)n;
        for (double d7 = 440.0 * 0.8181818181818182 / (double)n * (d5 + d6); d7 < 703.6363636363636 * 0.5116279069767442 / (double)n * (d5 + 1.0 - d6) - 1.2191780821917808 * 0.4101123595505618; d7 += 1.0) {
            double d8 = -d7 * (13.166666666666666 * 0.23860197369036404) / (38.57142857142857 * 4.666666666666667);
            double d9 = (-d7 - 1.0) * (0.6153635094660419 * 5.105263157894737) / (1.0945945945945945 * 164.44444444444446);
            double[] dArray = new double[0xFFFFA804 & 0x570F];
            dArray[0x31 & 0xFFFFCB84] = Math.cos(d8) * d3;
            dArray[0x83 & 0x3619] = -Math.sin(d8) * d3;
            dArray[0x42 & 0x5612] = Math.cos(d9) * d3;
            dArray[0xF & 0x4023] = -Math.sin(d9) * d3;
            double[] dArray2 = dArray;
            double[] dArray3 = new double[0x1B06 & 0xA4];
            dArray3[0x1018 & 0xFFFFE420] = Math.cos(d8) * d4;
            dArray3[0xFFFFC081 & 0x407] = -Math.sin(d8) * d4;
            dArray3[0xE02 & 0x83] = Math.cos(d9) * d4;
            dArray3[0x2A33 & 0xFFFF950F] = -Math.sin(d9) * d4;
            double[] dArray4 = dArray3;
            lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(0x620F & 0x8D7, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray4[0xFFFFE498 & 0x900], d2 + dArray4[0xFFFF8089 & 0x3007], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray4[0x8AA & 0x4703], d2 + dArray4[0xFFFF826B & 0x3507], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray2[0x5532 & 3], d2 + dArray2[0x933 & 0xFFFFB007], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIllIllllIllIlIIIllIIllll.llIIlIlIllIlIIIllIllllIlI(d + dArray2[0xFFFF8A0C & 0x2180], d2 + dArray2[0x3203 & 0x7D], 0.0).IIIlIlIIlllllIIIlllIllIll();
            lIlIlIIIIlIlIlllIIIllllIl.llIIlIlIllIlIIIllIllllIlI();
        }
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public static void IllllIllIIIlllIIllllllIII(float f, float f2, float f3, int n) {
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII(n);
        float f4 = (f -= f3 / 2.0f) + f3;
        float f5 = f2 + f3;
        IIlIllIIllllIIlllIllIllll iIlIllIIllllIIlllIllIllll = IIlIllIIllllIIlllIllIllll.lllIllIllIlIIIlllIIllllII();
        lIIIllIlIIIlIllIlIIllllIl lIIIllIlIIIlIllIlIIllllIl2 = iIlIllIIllllIIlllIllIllll.IllllIllIIIlllIIllllllIII();
        lIIIllIlIIIlIllIlIIllllIl2.lllIllIllIlIIIlllIIllllII(0x484 & 0x6826, lllIIIlllIlIlllIlIIllIlIl.IIIlIlIIlllllIIIlllIllIll);
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f2, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f, (double)f5, 0.0).IIIlIlIIlllllIIIlllIllIll();
        lIIIllIlIIIlIllIlIIllllIl2.llIIlIlIllIlIIIllIllllIlI((double)f4, (double)(f2 + (f5 - f2) / 2.0f), 0.0).IIIlIlIIlllllIIIlllIllIll();
        iIlIllIIllllIIlllIllIllll.llIIlIlIllIlIIIllIllllIlI();
        lIIIlIIIllIllIIIlllIlllII.lllIIllllIIlIIIlIIIIllIlI();
    }

    public void lllIllIllIlIIIlllIIllllII(List<String> list, int n, int n2) {
        int n3;
        int n4 = 0x1440 & 0xFFFFEBB2;
        for (String string : list) {
            n3 = this.lIlIIllIlIlIIlIlllIIllIII.lllIllIllIlIIIlllIIllllII(string);
            if (n3 <= n4) continue;
            n4 = n3;
        }
        int n5 = n + (0x205C & 0x80F);
        int n6 = n2 - (0x83D & 0x40C);
        n3 = 0x408 & 0x2188;
        int n7 = 0xF4FC8018 & 0xF0101910;
        this.lllIllIllIlIIIlllIIllllII(n5 - (0x4027 & 0x249B), n6 - (0x2446 & 0x1A24), n5 + n4 + (0x7A13 & 0xFFFF8183), n6 - (0x2013 & 0xFFFF80AF), n7, n7, 0.101265825f * 1975.0f);
        this.lllIllIllIlIIIlllIIllllII(n5 - (0x893 & 3), n6 + n3 + (0x43 & 0xFFFFE483), n5 + n4 + (0xC3 & 0xC0B), n6 + n3 + (0x2424 & 0x4985), n7, n7, 41.463417f * 4.8235292f);
        this.lllIllIllIlIIIlllIIllllII(n5 - (0x223 & 0xFFFF8C0B), n6 - (0xFFFF943B & 0x6843), n5 + n4 + (0xFFFFAA0B & 7), n6 + n3 + (0xFFFF804B & 0x423), n7, n7, 295.08197f * 0.67777777f);
        this.lllIllIllIlIIIlllIIllllII(n5 - (0x114 & 0x4E66), n6 - (0x227 & 0x483), n5 - (0x4D4B & 3), n6 + n3 + (0x903 & 0xFFFFA00F), n7, n7, 14.0f * 14.285714f);
        this.lllIllIllIlIIIlllIIllllII(n5 + n4 + (0xAA7 & 0x240B), n6 - (0x4013 & 0xC8B), n5 + n4 + (0xFFFFB40C & 0x4125), n6 + n3 + (0x223 & 0xFFFFA083), n7, n7, 133.33333f * 1.5f);
        int n8 = 0x745003FF & 0x505004FF;
        int n9 = (n8 & (0x20FFFEFE & 0x5FEFEFE)) >> (0x4121 & 0xFFFFA213) | n8 & (0xFF1B408C & 0xFF202420);
        this.lllIllIllIlIIIlllIIllllII(n5 - (0xFFFF8223 & 0x241B), n6 - (0xFFFF8007 & 0x583) + (0x1205 & 0x6481), n5 - (0x20B & 0xFFFFB883) + (0x6821 & 0x601), n6 + n3 + (0x5113 & 0xFFFF80A3) - (0xFFFF9281 & 0x39), n8, n9, 292.06348f * 0.6847826f);
        this.lllIllIllIlIIIlllIIllllII(n5 + n4 + (0x1002 & 0xFFFFE2C3), n6 - (0x2007 & 0xFFFFC103) + (0x4669 & 0x3883), n5 + n4 + (0x543 & 0xFFFFEA0F), n6 + n3 + (0x4183 & 0xFFFF8C53) - (0x101 & 0x4851), n8, n9, 1.21875f * 164.10257f);
        this.lllIllIllIlIIIlllIIllllII(n5 - (0xFFFFC007 & 0x18C3), n6 - (3 & 0x6503), n5 + n4 + (0x93 & 0xFFFFD043), n6 - (0xFFFFA103 & 0x1643) + (0xFFFFC401 & 0x2A39), n8, n8, 2342.8572f * 0.085365854f);
        this.lllIllIllIlIIIlllIIllllII(n5 - (0x1103 & 0x2487), n6 + n3 + (0x40B & 0xFFFFA882), n5 + n4 + (0xFFFF8103 & 0x643), n6 + n3 + (0x1703 & 0xFFFF800F), n9, n9, 23.404255f * 8.545455f);
        lIIllIlIIlllIlIlIlllIlIlI.llIIlIlIllIlIIIllIllllIlI(0.0f, 0.0f, 277.57144f * 0.7241379f);
        for (int i = 0x202 & 0x180; i < list.size(); ++i) {
            String string = (String)list.get(i);
            IlIIllIIllIIIllllIIllIIII.lIIIlIIIlIllIlllIIIIIlIlI().lllIllIllIlIIIlllIIllllII(string, n5, n6, 0xFFFFFFFF & 0xFFFFFFFF);
            if (i == 0) {
                n6 += 2;
            }
            n6 += 10;
        }
    }

    public static void llIIlIlIllIlIIIllIllllIlI(float f, float f2, float f3, float f4, float f5, int n, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        int n2;
        double d = f + f3;
        double d2 = f2 + f4;
        float f6 = (float)(n >> (0x7198 & 0xFFFF801E) & (0x30FF & 0x45FF)) / (269.43396f * 0.9464286f);
        float f7 = (float)(n >> (0x790 & 0xFFFF9010) & (0x10FF & 0x66FF)) / (14.4f * 17.708334f);
        float f8 = (float)(n >> (0x148C & 0x282A) & (0xFFFF90FF & 0x7FF)) / (1511.7858f * 0.16867469f);
        float f9 = (float)(n & (0x12FF & 0x45FF)) / (885.7895f * 0.28787878f);
        GL11.glPushAttrib(0x200C & 0x1331);
        GL11.glScaled(0.8857142857142857 * 0.5645161290322581, 1.3863636363636365 * 0.360655737704918, 0.4930555555555556 * 1.0140845070422535);
        f *= 2.0f;
        f2 *= 2.0f;
        d *= 0.6923076923076923 * 2.888888888888889;
        d2 *= 12.666666666666668 * 0.15789473684210525;
        lIIllIlIIlllIlIlIlllIlIlI.IllIIllIlIlIlIlllIlIlIllI();
        lIIllIlIIlllIlIlIlllIlIlI.lIlIIllIlIlIIlIlllIIllIII();
        lIIllIlIIlllIlIlIlllIlIlI.IllllIllIIIlllIIllllllIII(f7, f8, f9, f6);
        GL11.glEnable(0xBE3 & 0x1B20);
        GL11.glBegin(0xFFFF8009 & 0x2F09);
        if (bl) {
            for (n2 = 0xFFFF882C & 0x4201; n2 <= (0x5A & 0x25A); n2 += 3) {
                GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (Math.PI * 1.0) / (0.21875 * 822.8571428571429)) * (double)(f5 * (-0.75609756f * 1.3225807f)), (double)(f2 + f5) + Math.cos((double)n2 * (5.375 * 0.5844823541562406) / (304.6153846153846 * 0.5909090909090909)) * (double)(f5 * (0.7647059f * -1.3076923f)));
            }
        } else {
            GL11.glVertex2d(f, f2);
        }
        if (bl3) {
            for (n2 = 0x7DA & 0xFFFF805F; n2 <= (0xFFFF80B4 & 0x21B6); n2 += 3) {
                GL11.glVertex2d((double)(f + f5) + Math.sin((double)n2 * (1.3150852968515414 * 2.388888888888889) / (7.285714285714286 * 24.705882352941178)) * (double)(f5 * (-0.20689654f * 4.8333335f)), d2 - (double)f5 + Math.cos((double)n2 * (7.571428571428571 * 0.4149273316061991) / (81.81818181818181 * 2.2)) * (double)(f5 * (0.2f * -5.0f)));
            }
        } else {
            GL11.glVertex2d(f, d2);
        }
        if (bl4) {
            for (n2 = 0xFFFF9621 & 0x2094; n2 <= (0x4A5A & 0xFA); n2 += 3) {
                GL11.glVertex2d(d - (double)f5 + Math.sin((double)n2 * (1.0236650219562247 * 3.0689655172413794) / (194.3181818181818 * 0.9263157894736842)) * (double)f5, d2 - (double)f5 + Math.cos((double)n2 * (0.20634920634920634 * 15.224641321242844) / (0.8969072164948454 * 200.68965517241378)) * (double)f5);
            }
        } else {
            GL11.glVertex2d(d, d2);
        }
        if (bl2) {
            for (n2 = 0x25E & 0xFFFFA17A; n2 <= (0x1F6 & 0xB5); n2 += 3) {
                GL11.glVertex2d(d - (double)f5 + Math.sin((double)n2 * (2.6927937030769655 * 1.1666666666666667) / (0.7058823529411765 * 254.99999999999997)) * (double)f5, (double)(f2 + f5) + Math.cos((double)n2 * (1.3793103448275863 * 2.2776546738526) / (0.6185567010309279 * 291.0)) * (double)f5);
            }
        } else {
            GL11.glVertex2d(d, f2);
        }
        GL11.glEnd();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        GL11.glDisable(0xFFFF8B60 & 0x5B32);
        lIIllIlIIlllIlIlIlllIlIlI.IlIllllIllllllllIIIlIIlII();
        lIIllIlIIlllIlIlIlllIlIlI.lllIllIllIIIIllIIlIIlIlll();
        GL11.glScaled(0.4583333333333333 * 4.363636363636364, 3.230769230769231 * 0.6190476190476191, 2.5925925925925926 * 0.7714285714285715);
        GL11.glPopAttrib();
    }
}
