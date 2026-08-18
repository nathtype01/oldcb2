/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : c'était déjà un module "Motion Blur" enregistré (id "motionBlur"
 * visible dans mods.json) qui n'a jamais vraiment marché : sa méthode
 * IIlIIIIIIlllllllllIIIIIII() (appelée dès que le slider "value" ou le
 * color-picker change) appelait mc.entityRenderer.loadShader(new
 * ResourceLocation("motionblur","motionblur")) -- donc CheatBreaker avait
 * bien prévu un vrai shader "motionblur" à l'époque mais l'asset a jamais été
 * inclus dans le jar résultat : FileNotFoundException direct dès que le
 * slider bouge une fois relié à un profil qui a une vraie valeur sauvegardée
 * (le listener se déclenche au chargement de la config) ça plante le jeu au
 * boot avec "Invalid motionblur: File not found" (repéré via un vrai crash
 * report -- variable jamais identifiée correctement avant, je pensais que
 * c'était juste un rechargement d'icone menu, erreur corrigée ici)
 * supprimé l'appel foireux la ligne ne fait plus que reset son flag "dirty"
 *
 * le vrai flou (port polyblur phosphor) est câblé à la main dans
 * lIIlIlIllIIIIlIlIlllIIIII (l'équivalent EntityRenderer, champ séparé
 * polyBlurPhosphorShader qui touche jamais theShaderGroup) ce module sert
 * juste de bouton on/off + slider d'intensité pour ce flou-là maintenant
 * ajout : instance statique auto-enregistrée dans le constructeur pour que
 * EntityRenderer puisse lire l'état enabled et la valeur du slider sans
 * avoir à retrouver le vrai ModuleManager (jamais localisé malgré plusieurs
 * passes de recherche -- singleton fait maison à la place, safe puisque ce
 * module n'est construit qu'une seule fois comme tous les autres)
 */
import com.google.common.collect.ImmutableList;
import generated.Strings1;
import java.util.List;

public class lllIIIllIIllIIIlIlllIIIll
extends IIIIIIllllllIIIIlIlIIlIlI {
    public static lllIIIllIIllIIIlIlllIIIll polyBlurInstance;
    public IIlIlIlIIllllllIIIlIlllII IIIlIlIIlllllIIIlllIllIll;
    public IIIllIIIlIIIlIIIIIIlIllll llIIIllIIlIIIlIllIllIIlII;
    public boolean lIllllIIllIllllllIllIIIll = false;

    @Override
    public List IIIlIlIIlllllIIIlllIllIll() {
        this.IIIlIlIIlllllIIIlllIllIll = new IIlIlIlIIllllllIIIlIlllII(Strings1.strings[0xFFFFA066 & 0x5063], Strings1.strings[0x6867 & 0xFFFF83E3], 0x1B51 & 0xFFFFC427, 0x409 & 0x4341, 0x408A & 0x1E2B);
        this.llIIIllIIlIIIlIllIllIIlII = new IIIllIIIlIIIlIIIIIIlIllll(Strings1.strings[0xFFFF906D & 0x6276], Strings1.strings[0x465 & 0xFFFF826F], 0xFFFFFFFF & 0xFFFFFFFF);
        return ImmutableList.of(this.IIIlIlIIlllllIIIlllIllIll, this.llIIIllIIlIIIlIllIllIIlII);
    }

    public IIlIlIlIIllllllIIIlIlllII llIllllllIllllllllIllIIll() {
        return this.IIIlIlIIlllllIIIlllIllIll;
    }

    public IIIllIIIlIIIlIIIIIIlIllll IIIlIllIlIIlIlIIIlIlIlIll() {
        return this.llIIIllIIlIIIlIllIllIIlII;
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, float f5, float f6, boolean bl) {
        this.lllIllIllIlIIIlllIIllllII(Strings1.strings[0x5077 & 0xE6], f, f2, f3, f4);
    }

    public void IIlIIIIIIlllllllllIIIIIII() {
        this.lIllllIIllIllllllIllIIIll = false;
    }

    @Override
    public IIIIlIlIllIlIIllIIlIlIIII llIIIllIIlIIIlIllIllIIlII() {
        return new IIIIlIlIllIlIIllIIlIlIIII(Strings1.strings[0xE6F & 0x20E7], Strings1.strings[0x12E6 & 0x4566], ImmutableList.of(lllllIIIIlIlIIIIlIIIIIIll.lllIllIllIlIIIlllIIllllII));
    }

    public lllIIIllIIllIIIlIlllIIIll() {
        super((0x2841 & 0x684) != 0);
        this.lllIllIllIlIIIlllIIllllII(lIIllllIIIIlIlIIIlIIlllIl.class, (Object x) -> this.lllIllIllIlIIIlllIIllllII((lIIllllIIIIlIlIIIlIIlllIl) x));
        this.IIIlIlIIlllllIIIlllIllIll.lllIllIllIlIIIlllIIllllII((Object n) -> {
            this.lIllllIIllIllllllIllIIIll = true;
            if (this.IllllIllIIIlllIIllllllIII.llIIIIIIlllIlIIlIlIIllIII == null) {
                this.IIlIIIIIIlllllllllIIIIIII();
            }
        });
        this.llIIIllIIlIIIlIllIllIIlII.lllIllIllIlIIIlllIIllllII((Object n) -> {
            this.lIllllIIllIllllllIllIIIll = true;
            if (this.IllllIllIIIlllIIllllllIII.llIIIIIIlllIlIIlIlIIllIII == null) {
                this.IIlIIIIIIlllllllllIIIIIII();
            }
        });
        ((IlIIIlIlIllllIIIIlIllllII)this.IllllIllIIIlllIIllllllIII.IlIllIIlllllIllIIIlIIIlll()).llIIlIlIllIlIIIllIllllIlI().put(Strings1.strings[0xFFFFA068 & 0x965], new llIIIlIIIIlIllIlllIIIlIll());
        polyBlurInstance = this;
    }

    public void lllIllIllIlIIIlllIIllllII(lIIllllIIIIlIlIIIlIIlllIl lIIllllIIIIlIlIIIlIIlllIl2) {
        if (!((IlIIIlIlIllllIIIIlIllllII)this.IllllIllIIIlllIIllllllIII.IlIllIIlllllIllIIIlIIIlll()).llIIlIlIllIlIIIllIllllIlI().containsKey(Strings1.strings[0x10E8 & 0x4073])) {
            ((IlIIIlIlIllllIIIIlIllllII)this.IllllIllIIIlllIIllllllIII.IlIllIIlllllIllIIIlIIIlll()).llIIlIlIllIlIIIllIllllIlI().put(Strings1.strings[0xFFFF90E0 & 0x561], new llIIIlIIIIlIllIlllIIIlIll());
        }
        if (this.lIllllIIllIllllllIllIIIll || this.IllllIllIIIlllIIllllllIII.llIIIIIIlllIlIIlIlIIllIII == null && (!this.IllllIllIIIlllIIllllllIII.lIlIlIIIIllllIIlllllllIlI.lllIllIllIlIIIlllIIllllII() || !this.IllllIllIIIlllIIllllllIII.lIlIlIIIIllllIIlllllllIlI.llIIIllIIlIIIlIllIllIIlII().llIIlIlIllIlIIIllIllllIlI().equalsIgnoreCase(Strings1.strings[0x96D & 0x4E1]))) {
            this.IIlIIIIIIlllllllllIIIIIII();
        }
    }
}
