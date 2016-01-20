package ohnosequencesbundles.statika

import ohnosequences.statika._, bundles._, instructions._


case object jellyfish extends Bundle() {

  def instructions: AnyInstructions = {
    // do someting here
    say(s"${bundleName} is installed")
  }

}
