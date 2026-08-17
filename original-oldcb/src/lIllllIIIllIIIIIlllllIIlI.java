/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : c'est l'ancien écran login mojang username/mdp (voir
 * lIIIllIIIIIlIllIlllIlllII.java pour où son affichage est désactivé maintenant)
 * gardé ici et fixé en filet de sécu vu qu'il reste deux autres endroits dans le
 * client qui peuvent encore le construire direct
 * le handler clic du bouton confirm et le handler touche entrée appelaient tous
 * les deux le vrai login yggdrasil username/mdp du jar
 * (IIIlIlIIlllllIIIlllIllIll...().lllIllIllIlIIIlllIIllllII(username, password))
 * qui échoue toujours maintenant vu que mojang a fermé cet endpoint complètement
 * y'a plus aucune combo compte/mdp qui peut marcher ici peu importe ce qui est tapé
 * les deux endroits sont remplacés par un résultat réussi en dur vu qu'une session
 * venant d'un vrai launcher (auth microsoft device-code etc) est déjà valide au
 * moment où ce process du jeu existe donc cet écran a plus rien à vérifier
 *
 * liste "could not load" d'origine via cfr :
 *  IIIlIlIIlllllIIIlllIllIll
 *  IIlIIllIllIlIIlIlIIlIIIII
 *  IlIIllIIlIIIIIllIIlIIIIII
 *  IlIIllIIllIIIllllIIllIIII
 *  IlIlIIlIIlIIlIIlIIIIlIIIl
 *  IlIlIIllIlllIIIlllllIIIlI
 *  generated.Strings12
 *  generated.Strings18
 *  it.unimi.dsi.fastutil.objects.Reference2LongMaps$SynchronizedMap
 *  it.unimi.dsi.fastutil.shorts.Short2LongRBTreeMap$Submap$SubmapIterator
 *  lIIIlIIIllIllIIIlllIlllII
 *  lIIlIlIIlIlIlllllllllIIII
 *  lllllllllIIIlIIllllllIIll
 *  org.lwjgl.input.Keyboard
 */
import generated.Strings12;
import generated.Strings18;
import org.lwjgl.input.Keyboard;

public class lIllllIIIllIIIIIlllllIIlI
extends IlIlIIllIlllIIIlllllIIIlI {
    public lllllllllIIIlIIllllllIIll lIllIllllIllIlIIIllIIllll;
    public float IIIIlllIIIlIlIlIIIIIlllIl = 1.0645162f * 291.2121f;
    public float lIlIlIIIIlIlIlllIIIllllIl = 2.8461537f * 45.67568f;
    public IlIlIIlIIlIIlIIlIIIIlIIIl IIIllIIIlllIIIIlIlIIIIlIl;
    public lllllllllIIIlIIllllllIIll IIIIIIllIlIIIIlIlllIllllI;
    public lIIlIlIIlIlIlllllllllIIII IIIIllIIIIIlIlIlllIIllIll;
    public lIIlIlIIlIlIlllllllllIIII IIlIIIllIIIlIlllIIIIllllI;
    public String llIllIlIlIIIIlIIIIllIllll;

    public void lllIllIllIlIIIlllIIllllII() {
    }

    public void lllIllIllIlIIIlllIIllllII(String string, long l) {
        this.llIllIlIlIIIIlIIIIllIllll = string;
        this.IIIllIIIlllIIIIlIlIIIIlIl = new IlIlIIlIIlIIlIIlIIIIlIIIl(this, l);
    }

    public void llIIlIlIllIlIIIllIllllIlI() {
        super.llIIlIlIllIlIIIllIllllIlI();
        Keyboard.enableRepeatEvents((0x1401 & 0x40A1) != 0);
        float f = this.lllllIIIlIIllllllllIlllIl() / 2.0f - 91.0f * 0.71428573f;
        float f2 = 28.098959f * 1.1566265f;
        this.lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - 2.0689654f * 36.25f, f + f2, 1.2222222f * 122.72727f, 0.68421054f * 21.923077f);
        this.IIIIIIllIlIIIIlIlllIllllI.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - 1.6666666f * 45.0f, f + f2 + 35.127274f * 0.6547619f, 67.24138f * 2.2307692f, 1.1351352f * 13.214285f);
        this.IIlIIIllIIIlIlllIIIIllllI.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - 26.612904f * 0.93939394f, f + f2 + 10.0f * 1.0f + 1.3333334f * 34.5f, 0.48192772f * 103.75f, 1.0f * 15.0f);
        this.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII(this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - 0.9714286f * 18.014706f, this.lllllIIIlIIllllllllIlllIl() / 2.0f + 0.8666667f * 75.0f + 1.1538461f * 8.666667f, 0.31343284f * 111.666664f, 12.770269f * 1.1746032f);
    }

    public void IllllIllIIIlllIIllllllIII() {
        Keyboard.enableRepeatEvents((0xFFFF9A91 & 0x6002) != 0);
    }

    public lIllllIIIllIIIIIlllllIIlI() {
        this.lIllIllllIllIlIIIllIIllll = new lllllllllIIIlIIllllllIIll(null, IIlIIllIllIlIIlIlIIlIIIII.lllIlIlllIIlIlIIlIlllIIlI(), Strings18.strings[0x973 & 0x4377], 0xBAFFFFFF & 0x20FFFFFF, 0x7DFFFFFF & 0x35FFFFFF);
        this.IIIIIIllIlIIIIlIlllIllllI = new lllllllllIIIlIIllllllIIll(null, IIlIIllIllIlIIlIlIIlIIIII.lllIlIlllIIlIlIIlIlllIIlI(), Strings12.strings[0x26C & 0xFFFFFF6A], 0x60FFFFFF & 0x29FFFFFF, 0x77FFFFFF & 0xB5FFFFFF);
        this.IIIIIIllIlIIIIlIlllIllllI.IIIlIlIIlllllIIIlllIllIll((0xFFFFA049 & 0x4EA5) != 0);
        this.IIlIIIllIIIlIlllIIIIllllI = new lIIlIlIIlIlIlllllllllIIII(null, Strings18.strings[0x43F6 & 0x174], IIlIIllIllIlIIlIlIIlIIIII.lllIlIlllIIlIlIIlIlllIIlI());
        this.IIlIIIllIIIlIlllIIIIllllI.lllIllIllIlIIIlllIIllllII((f, f2, n) -> {
            String string = this.lIllIllllIllIlIIIllIIllll.IIIlIlIIlllllIIIlllIllIll();
            String string2 = this.IIIIIIllIlIIIIlIlllIllllI.IIIlIlIIlllllIIIlllIllIll();
            if (string.isEmpty() || string2.isEmpty()) {
                this.lllIllIllIlIIIlllIIllllII(Strings18.strings[0xB77 & 0x41F7], 0x16310BB9L & 0x8429FB8L);
                return (0xFFFFA050 & 0x40B) != 0;
            }
            boolean bl = true;
            IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII((lIlllIIllIllIlIlIlllIlIlI) null);
            return bl;
        });
        this.IIIIllIIIIIlIlIlllIIllIll = new lIIlIlIIlIlIlllllllllIIII(null, Strings18.strings[0x1975 & 0x4177], IIlIIllIllIlIIlIlIIlIIIII.lllIlIlllIIlIlIIlIlllIIlI());
        this.IIIIllIIIIIlIlIlllIIllIll.lllIllIllIlIIIlllIIllllII((f, f2, n) -> {
            this.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII((lIlllIIllIllIlIlIlllIlIlI) null);
            return (0x481 & 0x4803) != 0;
        });
        this.lIllllIIllIllllllIllIIIll.add(this.lIllIllllIllIlIIIllIIllll);
        this.lIllllIIllIllllllIllIIIll.add(this.IIIIIIllIlIIIIlIlllIllllI);
        this.lIllllIIllIllllllIllIIIll.add(this.IIlIIIllIIIlIlllIIIIllllI);
        this.lIllllIIllIllllllIllIIIll.add(this.IIIIllIIIIIlIlIlllIIllIll);
    }

    public void llIIlIlIllIlIIIllIllllIlI(float f, float f2, int n) {
    }

    // renommé depuis IIIlIlIIlllllIIIlllIllIll() : le vrai bytecode a ça qui coexiste
    // avec une méthode void sans rapport avec exactement le même nom obfusqué hérité
    // de IlIlIIllIlllIIIlllllIIIlI - légal au niveau bytecode (distingué par le
    // descriptor complet type de retour inclus) mais pas exprimable en java source
    // qui distingue les overloads que par les types de paramètres
    // appelé nulle part ailleurs dans cette classe de toute façon cet écran s'affiche
    // plus tout seul (voir lIIIllIIIIIlIllIlllIlllII.java)
    public lllllllllIIIlIIllllllIIll getPasswordField() {
        return this.IIIIIIllIlIIIIlIlllIllllI;
    }

    public void lllIllIllIlIIIlllIIllllII(float f, float f2, int n) {
    }

    // même situation que getPasswordField() au dessus renommé depuis IlIllIIIIlIllllIlIIlIIlll()
    public lllllllllIIIlIIllllllIIll getUsernameField() {
        return this.lIllIllllIllIlIIIllIIllll;
    }

    public void lllIllIllIlIIIlllIIllllII(float f, float f2) {
        super.lllIllIllIlIIIlllIIllllII(f, f2);
        float f3 = this.IIlIIIIIIlllllllllIIIIIII() / 2.0f - 203.94737f * 0.76f;
        float f4 = this.lllllIIIlIIllllllllIlllIl() / 2.0f - 1.4444444f * 45.0f;
        lIIIlIIIllIllIIIlllIlllII.lllIllIllIlIIIlllIIllllII((float)f3, (float)f4, (float)(0.76f * 407.89474f), (float)(98.57143f * 1.3188406f), (float)(6.2f * 0.8064516f), (int)(0x200116A5 & 0xF840A040), (int)(0x21FFFFFF & 0xF0FFFFFF), (int)(0xB04C6023 & 0x318000D0));
        if (this.IIIllIIIlllIIIIlIlIIIIlIl != null && this.IIIllIIIlllIIIIlIlIIIIlIl.lIIllIIlIIIllIlIIllIIlIll()) {
            int n = IlIIllIIlIIIIIllIIlIIIIII.lllIllIllIlIIIlllIIllllII((float)1.0f, (float)(0.6923077f * 0.21666667f), (float)(0.19800001f * 0.75757575f), (float)(0.03125f * 24.0f * this.IIIllIIIlllIIIIlIlIIIIlIl.lllIllIllIlIIIlllIIllllII()));
            IIlIIllIllIlIIlIlIIlIIIII.IllllIllIIIlllIIllllllIII().llIIlIlIllIlIIIllIllllIlI(this.llIllIlIlIIIIlIIIIllIllll, this.IIlIIIIIIlllllllllIIIIIII() / 2.0f, f4 + 1.7777778f * 6.75f, n);
        }
        IIlIIllIllIlIIlIlIIlIIIII.llIllllllIllllllllIllIIll().llIIlIlIllIlIIIllIllllIlI(Strings18.strings[0xFFFF81F6 & 0xB7E], this.IIlIIIIIIlllllllllIIIIIII() / 2.0f, f4 - 58.57143f * 0.5121951f + 1.0f, 0x70C5411A & 0xAA308E01);
        IIlIIllIllIlIIlIlIIlIIIII.llIllllllIllllllllIllIIll().llIIlIlIllIlIIIllIllllIlI(Strings18.strings[0x1776 & 0xFFFF81FE], this.IIlIIIIIIlllllllllIIIIIII() / 2.0f, f4 - 0.537037f * 55.862072f, 0xFFBEE7BF & 0xFFBEDBBD);
    }

    public void lllIllIllIlIIIlllIIllllII(char c, int n) {
        if (n == (0xF & 0x6C0F)) {
            if (this.lIllIllllIllIlIIIllIIllll.lllllIIIlIIllllllllIlllIl()) {
                this.lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII((0x3498 & 0x900) != 0);
                this.IIIIIIllIlIIIIlIlllIllllI.lllIllIllIlIIIlllIIllllII((0x109 & 0x10E7) != 0);
            } else if (this.IIIIIIllIlIIIIlIlllIllllI.lllllIIIlIIllllllllIlllIl()) {
                this.IIIIIIllIlIIIIlIlllIllllI.lllIllIllIlIIIlllIIllllII((0xF00 & 0x8C) != 0);
                this.lIllIllllIllIlIIIllIIllll.lllIllIllIlIIIlllIIllllII((1 & 5) != 0);
            }
        } else if (n == (0x21F & 0x93C)) {
            String string = this.lIllIllllIllIlIIIllIIllll.IIIlIlIIlllllIIIlllIllIll();
            String string2 = this.IIIIIIllIlIIIIlIlllIllllI.IIIlIlIIlllllIIIlllIllIll();
            if (string.isEmpty() || string2.isEmpty()) {
                this.lllIllIllIlIIIlllIIllllII(Strings18.strings[0x1F7 & 0x117F], 0x72AC0BB9L & 0xA6361A038C024BB8L);
                return;
            }
            boolean bl = true;
            IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII((lIlllIIllIllIlIlIlllIlIlI) null);
            if (bl) {
                lIIIlIIIllIllIIIlllIlllII.llIllllllIllllllllIllIIll();
            }
        }
    }
}
