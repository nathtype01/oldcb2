/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : la condition ligne 53 conditionnait l'ancien écran login mojang (le popup
 * "please log in" avec ses champs login/mdp) sur "pas encore authentifié"
 * tout ce flow parle au authserver mojang que mojang a fermé y'a des années pour
 * le login direct login/mdp du coup sur ce build "pas authentifié" était toujours vrai
 * et cet écran se remplaçait à chaque tick d'où l'impression que c'était freeze avec
 * les champs qui se vidaient tout seuls (en vrai nouvelle instance vide à chaque tick pas la même)
 * le jeu est toujours lancé avec une session déjà valide maintenant (auth microsoft via
 * le launcher officiel ou n'importe quel vrai launcher) donc cette revérif legacy sert à rien
 * forcé à false en dessous pour toujours passer à la suite de la méthode
 * qui elle remplace le menu principal vanilla par celui du client
 * l'ancien fix plus bourrin (skip toute la méthode) désactivait ce swap par accident
 * celui là touche que le check
 */
import generated.Strings9;
import java.util.concurrent.TimeUnit;
import org.lwjgl.input.Keyboard;

public class lIIIllIIIIIlIllIlllIlllII
implements IIlllIIIIIlllIllIIIllIlIl {
    public long lllIllIllIlIIIlllIIllllII;

    public lIIIllIIIIIlIllIlllIlllII() {
        // lambdas typées ici au lieu des method references que cfr sortait
        // 4 overloads avec le même nom déobfusqué donc this::lllIllIllIlIIIlllIIllllII
        // est ambigu pour javac sans type cible pour résoudre (la signature de la
        // méthode d'enregistrement vit dans une interface que cfr a pas pu décompiler)
        // un type explicite choisit direct le bon overload ça compile pareil au final
        this.lllIllIllIlIIIlllIIllllII(IlIIllIIlIlIIIIlIlIIlllII.class, (Object x) -> this.lllIllIllIlIIIlllIIllllII((IlIIllIIlIlIIIIlIlIIlllII) x));
        this.lllIllIllIlIIIlllIIllllII(lllIIIlllllIIlIIlIllllIII.class, (Object x) -> this.lllIllIllIlIIIlllIIllllII((lllIIIlllllIIlIIlIllllIII) x));
        this.lllIllIllIlIIIlllIIllllII(IIlIIIIIIIIlIIIIIlIlIllII.class, (Object x) -> this.lllIllIllIlIIIlllIIllllII((IIlIIIIIIIIlIIIIIlIlIllII) x));
        this.lllIllIllIlIIIlllIIllllII(lllIIlllllIIllIlIIIlIllll.class, (Object x) -> this.lllIllIllIlIIIlllIIllllII((lllIIlllllIIllIlIIIlIllll) x));
    }

    public void lllIllIllIlIIIlllIIllllII(lllIIlllllIIllIlIIIlIllll lllIIlllllIIllIlIIIlIllll2) {
        if (lllIIlllllIIllIlIIIlIllll2.lllIllIllIlIIIlllIIllllII() instanceof lIlllIlIIlIlIlIllIllIlIlI) {
            this.lllIllIllIlIIIlllIIllllII((lIlllIlIIlIlIlIllIllIlIlI)lllIIlllllIIllIlIIIlIllll2.lllIllIllIlIIIlllIIllllII());
        }
    }

    public void lllIllIllIlIIIlllIIllllII(IlIIllIIlIlIIIIlIlIIlllII ilIIllIIlIlIIIIlIlIIlllII) {
        if (IlIIllIIllIIIllllIIllIIII.lIllllIIllIllllllIllIIIll() == null && false) {
            ilIIllIIlIlIIIIlIlIIlllII.lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)new lIllllIIIllIIIIIlllllIIlI());
            return;
        }
        if (ilIIllIIlIlIIIIlIlIIlllII.IllllIllIIIlllIIllllllIII() != null) {
            if (ilIIllIIlIlIIIIlIlIIlllII.IllllIllIIIlllIIllllllIII() instanceof llIIIIlIlIIllIlIIIIIlIlll) {
                ilIIllIIlIlIIIIlIlIIlllII.lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)new llllIIIlllIIIllIlllIIlIlI());
            }
        } else if (IlIIllIIllIIIllllIIllIIII.lIllllIIllIllllllIllIIIll() == null) {
            ilIIllIIlIlIIIIlIlIIlllII.lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)new llllIIIlllIIIllIlllIIlIlI());
        }
    }

    public void lllIllIllIlIIIlllIIllllII(IIlIIIIIIIIlIIIIIlIlIllII iIlIIIIIIIIlIIIIIlIlIllII) {
        if (iIlIIIIIIIIlIIIIIlIlIllII.IllllIllIIIlllIIllllllIII() instanceof lIlllIlIIlIlIlIllIllIlIlI && iIlIIIIIIIIlIIIIIlIlIllII.IlIllIIIIlIllllIlIIlIIlll().lIllllIIllIllllllIllIIIll == (0x8EC & 0x266)) {
            IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)new IIlIllIIlIIllIlIIIlIIlIll(IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII));
        }
    }

    public void lllIllIllIlIIIlllIIllllII(lIlllIlIIlIlIlIllIllIlIlI lIlllIlIIlIlIlIllIllIlIlI2) {
        lIIlIlIlIlIllIIlIIllllIll lIIlIlIlIlIllIIlIIllllIll2 = new lIIlIlIlIlIllIIlIIllllIll(0x664 & 0xFFFFD87C, lIlllIlIIlIlIlIllIllIlIlI2.IIIlIllIlIIlIlIIIlIlIlIll / (0xFFFF8012 & 0x402) - (0xAE4 & 0x5067), lIlllIlIIlIlIlIllIllIlIlI2.IIlIIIIIIlllllllllIIIIIII / (0x7806 & 0x2AD) + (0x38B9 & 0x4238), 0xFFFFA2CA & 0x8FC, 0xFFFF90BC & 0x57, Strings9.strings[0x10EC & 0xFFFFC2F7]);
        lIlllIlIIlIlIlIllIllIlIlI2.llIllIlIlIIIIlIIIIllIllll().add(lIIlIlIlIlIllIIlIIllllIll2);
    }

    public void lllIllIllIlIIIlllIIllllII(lllIIIlllllIIlIIlIllllIII lllIIIlllllIIlIIlIllllIII2) {
        if (lllIIIlllllIIlIIlIllllIII2.IlIllIIIIlIllllIlIIlIIlll() == IlllllIIIlllIlllIlIlIllll.llIIlIlIllIlIIIllIllllIlI) {
            llIIIllIlIIIIIIllIIIIllII llIIIllIlIIIIIIllIIIIllII2 = IlIIllIIllIIIllllIIllIIII.llIIlIlIllIlIIIllIllllIlI().lIIIlIIIlIllIlllIIIIIlIlI().IllllIllIIIlllIIllllllIII().llllIllIllIlIlIlIIIlIllll();
            if (llIIIllIlIIIIIIllIIIIllII2.lllIllIllIlIIIlllIIllllII() && !((IlIllllIlIIIIIlIlllIIIlII)llIIIllIlIIIIIIllIIIIllII2.IIlllllllllIlIllIlIlIIllI()).IllllIllIIIlllIIllllllIII() && !((IlIllllIlIIIIIlIlllIIIlII)llIIIllIlIIIIIIllIIIIllII2.IIlllllllllIlIllIlIlIIllI()).lllIllIllIlIIIlllIIllllII() && !((IlIllllIlIIIIIlIlllIIIlII)llIIIllIlIIIIIIllIIIIllII2.IIlllllllllIlIllIlIlIIllI()).llIIlIlIllIlIIIllIllllIlI() && IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII == null) {
                lllIIIlllllIIlIIlIllllIII2.lllIllIllIlIIIlllIIllllII();
                IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)lIIIIlIllIIlllIIlIIIIlllI.lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII));
                return;
            }
            if (((IlIllllIlIIIIIlIlllIIIlII)llIIIllIlIIIIIIllIIIIllII2.IIlllllllllIlIllIlIlIIllI()).llIIlIlIllIlIIIllIllllIlI() && lllIIIlllllIIlIIlIllllIII2.IllllIllIIIlllIIllllllIII() == (0x3C2A & 0x13B) || ((IlIllllIlIIIIIlIlllIIIlII)llIIIllIlIIIIIIllIIIIllII2.IIlllllllllIlIllIlIlIIllI()).lllIllIllIlIIIlllIIllllII() && lllIIIlllllIIlIIlIllllIII2.IllllIllIIIlllIIllllllIII() == (0x1E39 & 0x413E) || ((IlIllllIlIIIIIlIlllIIIlII)llIIIllIlIIIIIIllIIIIllII2.IIlllllllllIlIllIlIlIIllI()).IllllIllIIIlllIIllllllIII() && lllIIIlllllIIlIIlIllllIII2.IllllIllIIIlllIIllllllIII() == (0x1F & 0x1C9D)) {
                this.lllIllIllIlIIIlllIIllllII = System.nanoTime();
            } else if (!((Boolean)IlIIllIIllIIIllllIIllIIII.llIIlIlIllIlIIIllIllllIlI().lIIIlIIIlIllIlllIIIIIlIlI().IllllIllIIIlllIIllllllIII().IIlIIIIIIlllllllllIIIIIII().IIlllllllllIlIllIlIlIIllI()).booleanValue() && llIIIllIlIIIIIIllIIIIllII2.lllIllIllIlIIIlllIIllllII() && !(IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII instanceof lIIIIlIllIIlllIIlIIIIlllI) && this.lllIllIllIlIIIlllIIllllII != (0x6FC8C94482011104L & 0x3010262AL) && (Keyboard.isKeyDown((int)(0x2F & 0x793A)) || Keyboard.isKeyDown((int)(0x1B8 & 0xFFFF8E79)) || Keyboard.isKeyDown((int)(0x581F & 0xFFFF82BD))) && System.nanoTime() - this.lllIllIllIlIIIlllIIllllII <= TimeUnit.MILLISECONDS.toNanos(0xC9F2DE9C900401FEL & 0x360D2163284231F4L)) {
                lllIIIlllllIIlIIlIllllIII2.lllIllIllIlIIIlllIIllllII();
                IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)lIIIIlIllIIlllIIlIIIIlllI.lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII));
                return;
            }
        }
        if (IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII != null && IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII.llllIllIllIlIlIlIIIlIllll) {
            IlIllIIIIllllIIIlIIlIIlIl.lllIllIllIlIIIlllIIllllII((int)lllIIIlllllIIlIIlIllllIII2.IllllIllIIIlllIIllllllIII(), (lllIIIlllllIIlIIlIllllIII2.IlIllIIIIlIllllIlIIlIIlll() == IlllllIIIlllIlllIlIlIllll.llIIlIlIllIlIIIllIllllIlI ? 0xFFFF8019 & 0x24C3 : 0x2938 & 0x4440) != 0);
            return;
        }
        if ((IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII == null || IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().llIIIIIIlllIlIIlIlIIllIII instanceof lIlIIIllIIlIIIIIlIIlIIIlI) && lllIIIlllllIIlIIlIllllIII2.IlIllIIIIlIllllIlIIlIIlll() == IlllllIIIlllIlllIlIlIllll.llIIlIlIllIlIIIllIllllIlI && lllIIIlllllIIlIIlIllllIII2.IllllIllIIIlllIIllllllIII() == ((Integer)IlIIllIIllIIIllllIIllIIII.llIIlIlIllIlIIIllIllllIlI().lIIIlIIIlIllIlllIIIIIlIlI().IllllIllIIIlllIIllllllIII().lllllIIIlIIllllllllIlllIl().IIlllllllllIlIllIlIlIIllI()).intValue() && !IIlIIlIlIIIIlIIIlllIlIlIl.lllIllIllIlIIIlllIIllllII()) {
            IlIIllIIllIIIllllIIllIIII.lllIllIllIlIIIlllIIllllII().lllIllIllIlIIIlllIIllllII((IlIllllIIlIIllIlIlllllIlI)new IllIlIIllIlIlIIlllIlIlllI());
        }
    }
}
