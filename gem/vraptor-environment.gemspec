# -*- encoding: utf-8 -*-
$:.push File.expand_path("../lib", __FILE__)
require "vraptor-environment/version"

Gem::Specification.new do |s|
  s.name        = "vraptor-environment"
  s.version     = VraptorEnvironment::VERSION
  s.platform    = Gem::Platform::RUBY
  s.authors     = ["Andrew Kurauchi", "Guilherme Silveira"]
  s.email       = ["andrew.kurauchi@caelum.com.br", "guilherme.silveira@caelum.com.br"]
  s.homepage    = ""
  s.summary     = "VRaptor Environment."
  s.description = "A simple vraptor plugin that adds an environment dependent configuration file."

  s.rubyforge_project = "vraptor-environment"

  s.files         = `git ls-files`.split("\n")
  s.test_files    = `git ls-files -- {test,spec,features}/*`.split("\n")
  s.executables   = `git ls-files -- bin/*`.split("\n").map{ |f| File.basename(f) }
  s.require_paths = ["lib"]
end
