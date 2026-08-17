import java.util.List;
import joptsimple.OptionException;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

/**
 * remplace optionset.valueof(optionspec) et optionspec.value(optionset)
 * jamais de crash multipleargumentsforoptionexception ici
 * comportement identique a l'original si 0 ou 1 valeur enregistrée (defauts compris)
 * seul changement : si jopt-simple a enregistré plus d'une valeur (le bug qu'on patch)
 * on prend la dernière au lieu de crash
 * "dernière valeur gagne" = comportement cli normal (l'argument explicite doit gagner sur le défaut) donc fallback safe
 *
 * voir llIIlIIIlllIllllIlIllIIlI.java (point d'entrée des args de lancement) pour l'usage
 * et le commentaire en haut de ce fichier pour le pourquoi
 */
public final class CBArgFix {

    private CBArgFix() {
    }

    public static Object safeValueOf(OptionSet optionSet, OptionSpec<?> spec) {
        try {
            return optionSet.valueOf(spec);
        } catch (OptionException e) {
            return lastValueOrNull(optionSet, spec);
        }
    }

    public static Object safeValue(OptionSpec<?> spec, OptionSet optionSet) {
        try {
            return spec.value(optionSet);
        } catch (OptionException e) {
            return lastValueOrNull(optionSet, spec);
        }
    }

    private static Object lastValueOrNull(OptionSet optionSet, OptionSpec<?> spec) {
        List<?> values = optionSet.valuesOf(spec);
        if (values.isEmpty()) {
            return null;
        }
        return values.get(values.size() - 1);
    }
}
