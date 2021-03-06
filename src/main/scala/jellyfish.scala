package ohnosequencesBundles.statika

import ohnosequences.statika._
import scala.sys.process._
import java.io.File

abstract class Jellyfish(val version: String) extends Bundle(cdevel) { jellyfish =>

  lazy val tarball: String = s"jellyfish-${jellyfish.version}.tar.gz"
  lazy val folder: String = s"jellyfish-${jellyfish.version}"
  lazy val jellyfishBin: String = "jellyfish"

  lazy val getTarball = cmd("wget")(
    s"https://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/jellyfish/${jellyfish.version}/${jellyfish.tarball}"
  )

  lazy val extractTarball = cmd("tar")( "-xvzf", jellyfish.tarball )

  lazy val configure = new SimpleInstructions( wd =>
    // TODO proper error handling
    Success("configuring Jellyfish build", Process(
        Seq("./configure"),
        new File(s"${wd.getCanonicalPath}/${jellyfish.folder}")
      ).!
    )
  )

  lazy val compile = cmd("make")("-C", jellyfish.folder)

  lazy val makeInstall = cmd("make")("-C", jellyfish.folder, "install")

  lazy val linkBinaries = cmd("ln")(
    "-s",
    new File(s"${jellyfish.folder}/bin/${jellyfish.jellyfishBin}").getCanonicalPath,
    s"/usr/bin/${jellyfish.jellyfishBin}"
  )

  def instructions: AnyInstructions = getTarball      -&-
                                      extractTarball  -&-
                                      configure       -&-
                                      compile         -&-
                                      makeInstall     -&-
                                      linkBinaries
}
